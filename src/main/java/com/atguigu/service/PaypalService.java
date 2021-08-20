package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.bean.MlfrontAddress;
import com.atguigu.bean.MlfrontOrderItem;
import com.atguigu.enumC.PaypalPaymentIntent;
import com.atguigu.enumC.PaypalPaymentMethod;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {
	
	//开发环境-shaohua
	String clientId = "AQyXf-N2nNr8QwJsFt7IudPRL-CMGYEXCCzgqOHIA037JLhSFOEchb2kGa_z_BqzKY4CmUPFiGqG_uNj";
	String clientSecret = "EO5N6EtaEiIQXF18UWWZJGGeB8VL4qMxC-jR4tvHoXJD0RBdZGzcCguUBuRgWNBR8Lk-ge8XRK379NCl";
	String mode="sandbox";
	
	//开发环境-boss
//	String clientId = "AZTtkPd6LowZG2KewtB3TAHIu3WeqcM6VCgp2xB4ShixmnAgOxskEgwdWEM9Qv0fc3Ln2DLVy6xPLPFN";
//	String clientSecret = "EANTizGHYqb_0rWbPKlDD-otKQwi0k7RN6xvDiWtZNZY1gSyO71VrCpzvy6xTzGPVC5VxB6U66wjndYM";
//	String mode="sandbox";
	
	//mogalook-live环境
//	String clientId="Ad0_fWFpJ2XCHI4xZY3mywHctvdm0rNIvltKnN3bxE_1j56ZK7b-HOzyhrw07ZZWFZRIBzUPJajU-CGW";
//	String clientSecret="ECTB6nSnyAo0S7W7rNiZCsiKMTG5qEOCRYO6wYDEO7sBsVU5rpAHDqVXwzqKhPriWGn39JfFXcmq1biq";
//    String mode="live";
    
	//陈鹏账户
//    String clientId="AbYhYseSfoEHsp02nyg6O3A1NowoKN00tWYvwAYErFKy0T7FDUkHENMMP7TTDqn0bP9LKISVJILgx3G5";
//    String clientSecret="EFOnl1UovBwjAUsbw8EzqGZd8rPdw7S0CAvy-2SxtQWbz2dl9FYF69sLptxxbcG2_8YjOIGZZwrCqlqN";
//    String mode="live";
	
	@Autowired
	MlfrontOrderService mlfrontOrderService;
	
	
//	@Autowired
//	private APIContext apiContext;
	APIContext apiContext = new APIContext(clientId, clientSecret, mode);
	
	//创建payMent参数
	public Payment createPayment(
            Double total, 
            String currency, 
            MlfrontAddress mlfrontAddress,
            List<MlfrontOrderItem> mlfrontOrderItemList,
            PaypalPaymentMethod method, 
            PaypalPaymentIntent intent, 
            String description, 
            String Shopdiscount, 
            String addressMoney, 
            String cancelUrl, 
            String successUrl) throws PayPalRESTException{
		      
        
		//获取总价格
  		String totalStr = (String.format("%.2f", total));
  		System.out.println(totalStr);
  		System.out.println(description);

  		// ###Transaction
  		// A transaction defines the contract of a
  		// payment - what is the payment for and who
  		// is fulfilling it. Transaction is created with
  		// a `Payee` and `Amount` types
  		Transaction transaction = new Transaction();
//  	transaction.setDescription("This is the payment transaction description.");
  		
  		transaction.setDescription(description);
  		
  		String subMoney = "";
  		
  		ItemList itemList = new ItemList();
  		if(mlfrontOrderItemList.size()>1){
  			itemList = getItemList(mlfrontOrderItemList);
  			subMoney = getItemListsMoney(mlfrontOrderItemList);
  		}else{
  			MlfrontOrderItem mlfrontOrderItem = mlfrontOrderItemList.get(0);
  			Item item = new Item();
  			String name=mlfrontOrderItem.getOrderitemPname();
  			String orderitemPskuNameDetail=mlfrontOrderItem.getOrderitemPskuName();
//  			if(name.length()>40){
//  				name= name.substring(0, 40);
//  				name=name+"...";
  				name=name+"("+orderitemPskuNameDetail+")"+".";
//  			}
  			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
  			String skuNumStr = skuNum+"";
  			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
  			String oneMoney = getOnemoney(skuNum,money);
//  		demo:	item.setName(name).setQuantity("1").setCurrency("USD").setPrice(money);
  			item.setName(name).setQuantity(skuNumStr).setCurrency("USD").setPrice(oneMoney);
  			money = getOneAllMoney(skuNum,oneMoney);
  			subMoney = money;
  			List<Item> items = new ArrayList<Item>();
  			items.add(item);
  			itemList.setItems(items);
  		}
  	  	//地址参数3.0
        ShippingAddress shippingAddress = getShippingAddress(mlfrontAddress);
//  		ShippingAddress shippingAddress = getShippingAddressSDK(mlfrontAddress);
        itemList.setShippingAddress(shippingAddress); 		
  		
  		Details details = new Details();
  		details.setShipping("0");
  		details.setSubtotal(subMoney);
  		details.setTax("0");//税
  		String shopdiscountMoney = "-"+Shopdiscount;
  		details.setShippingDiscount(shopdiscountMoney);
  		details.setShipping((addressMoney));

  		// ###Amount
  		// Let's you specify a payment amount.
  		Amount amount = new Amount();
  		amount.setCurrency("USD");
  		// Total must be equal to sum of shipping, tax and subtotal.
  		
  		String amTotal = getamountTotal(subMoney,Shopdiscount,addressMoney);
  		amount.setTotal(amTotal);
  		amount.setDetails(details);
  		transaction.setAmount(amount);
  		transaction.setItemList(itemList);
  		
  		
  		// The Payment creation API requires a list of
  		// Transaction; add the created `Transaction`
  		// to a List
  		List<Transaction> transactions = new ArrayList<Transaction>();
  		transactions.add(transaction);

  		// ###Payer
  		// A resource representing a Payer that funds a payment
  		// Payment Method
  		// as 'paypal'
  		Payer payer = new Payer();
  		payer.setPaymentMethod("paypal");

  		// ###Payment
  		// A Payment Resource; create one using
  		// the above types and intent as 'sale'
  		Payment payment = new Payment();
  		payment.setIntent("sale");
  		payment.setPayer(payer);
  		payment.setTransactions(transactions);
  		System.out.println(payment.getTransactions().get(0).getAmount().toJSON());
  		
  		RedirectUrls redirectUrls = new RedirectUrls();
  		redirectUrls.setCancelUrl(cancelUrl);
  		redirectUrls.setReturnUrl(successUrl);
  		payment.setRedirectUrls(redirectUrls);

 // 		System.out.println(payment.toJSON());
        return payment.create(apiContext);
    }

	private String getamountTotal(String subMoney, String shopdiscount, String addressMoney) {
		
		Double subMoneyDou = Double.parseDouble(subMoney);
		
		Double shopdiscountDou = Double.parseDouble(shopdiscount);
		
		Double addressMoneyDou = Double.parseDouble(addressMoney);
		
		Double amountTotalDou = subMoneyDou - shopdiscountDou + addressMoneyDou;
		
		String amountTotalDouStr = (String.format("%.2f", amountTotalDou));
		
		return amountTotalDouStr;
	}

	private String getOneAllMoney(Integer skuNum, String oneMoney) {
		Double oneMoneyDou = new Double(oneMoney);
		Double OneAllM = oneMoneyDou*skuNum;
		String OneAllMoney = String.format("%.2f", OneAllM);
		return OneAllMoney;
	}

	private String getOnemoney(Integer skuNum, String money) {
		System.out.println("paypalService中的:getOnemoney"+money+",skuNum:"+skuNum);
		Double moneyAll = new Double(money);
		System.out.println("paypalService中的Double moneyAll = new Double(money)+moneyAll:"+moneyAll);
		Double oneM = moneyAll/skuNum;
		String Onemoney = String.format("%.2f", oneM);
		return Onemoney;
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
	
	private ItemList getItemList(List<MlfrontOrderItem> mlfrontOrderItemList) {
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		//System.out.println("paypalService中的结算单子的详情"+mlfrontOrderItemList.toString());
		for(MlfrontOrderItem mlfrontOrderItem:mlfrontOrderItemList){
			Item item = new Item();
			String name=mlfrontOrderItem.getOrderitemPname();
			String orderitemPskuNameDetail=mlfrontOrderItem.getOrderitemPskuName();
//			if(name.length()>40){
 // 				name= name.substring(0, 40);
//  				name=name+"...";
  				name=name+"("+orderitemPskuNameDetail+")"+".";
//  			}
			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
			String skuNumStr = skuNum+"";
			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
			String oneMoney =getOnemoney(skuNum,money);
			item.setName(name).setQuantity(skuNumStr).setCurrency("USD").setPrice(oneMoney);
			money = getOneAllMoney(skuNum,oneMoney);
			
			items.add(item);
        }
		itemList.setItems(items);
		return itemList;
	}
	
	private String getItemListsMoney(List<MlfrontOrderItem> mlfrontOrderItemList) {
		
		Double MoneyDuball=new Double("0.00");
		for(MlfrontOrderItem mlfrontOrderItem:mlfrontOrderItemList){
			String name=mlfrontOrderItem.getOrderitemPname();
			if(name.length()>40){
  				name= name.substring(0, 40);
  				name=name+"...";
  			}
			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
			String oneMoney =getOnemoney(skuNum,money);
			money = getOneAllMoney(skuNum,oneMoney);
			Double MoneyDub=new Double(money);
			MoneyDuball=MoneyDuball+ MoneyDub;
			
        }
		String OneAllMoney = String.format("%.2f", MoneyDuball);
		return OneAllMoney;
	}
	
	private ShippingAddress getShippingAddress(MlfrontAddress mlfrontAddress) {
		
		ShippingAddress shippingAddress = new ShippingAddress();
		String RecipientName = mlfrontAddress.getAddressUserfirstname()+" "+mlfrontAddress.getAddressUserlastname();
		shippingAddress.setRecipientName(RecipientName);
		String Line1 =mlfrontAddress.getAddressDetail();
		shippingAddress.setLine1(Line1);
		String City = mlfrontAddress.getAddressCity();
		shippingAddress.setCity(City);
		String state = mlfrontAddress.getAddressProvincecode();
		shippingAddress.setState(state);
		String Phone = mlfrontAddress.getAddressTelephone();
		shippingAddress.setPhone(Phone);
		String PostalCode = mlfrontAddress.getAddressPost();
		shippingAddress.setPostalCode(PostalCode);
		String CountryCode = mlfrontAddress.getAddressCountryCode();
        shippingAddress.setCountryCode(CountryCode);
		return shippingAddress;
	}
	
	//这是个地址对象的封装demo,往PayPal传参所需
//	private ShippingAddress getShippingAddressSDK(MlfrontAddress mlfrontAddress) {
//		ShippingAddress shippingAddress = new ShippingAddress();
//		shippingAddress.setRecipientName("Thomas Miller");
//		shippingAddress.setLine1("4th Floor, One Lagoon Drive");
//		shippingAddress.setLine2("Unit #34");
//		shippingAddress.setCity("Redwood City");
//		shippingAddress.setState("CA");
//		shippingAddress.setPhone("011862212345678");
//		shippingAddress.setPostalCode("94065");
//        shippingAddress.setCountryCode("US");
//		return shippingAddress;
//	}
	
	
	public Payment proDetailCreatePayment(
            Double total, 
            String currency, 
            MlfrontAddress mlfrontAddress,
            List<MlfrontOrderItem> mlfrontOrderItemList,
            PaypalPaymentMethod method, 
            PaypalPaymentIntent intent, 
            String description, 
            String Shopdiscount, 
            String addressMoney, 
            String cancelUrl, 
            String successUrl) throws PayPalRESTException{
		      
        
      //获取总价格
  		String totalStr = (String.format("%.2f", total));
  		System.out.println(totalStr);
  		System.out.println(description);

  		// ###Transaction
  		// A transaction defines the contract of a
  		// payment - what is the payment for and who
  		// is fulfilling it. Transaction is created with
  		// a `Payee` and `Amount` types
  		Transaction transaction = new Transaction();
//  	transaction.setDescription("This is the payment transaction description.");
  		
  		transaction.setDescription(description);
  		
  		String subMoney = "";
  		
  		ItemList itemList = new ItemList();
  		if(mlfrontOrderItemList.size()>1){
  			itemList = getItemList(mlfrontOrderItemList);
  			subMoney = getItemListsMoney(mlfrontOrderItemList);
  		}else{
  			MlfrontOrderItem mlfrontOrderItem = mlfrontOrderItemList.get(0);
  			Item item = new Item();
  			String name=mlfrontOrderItem.getOrderitemPname();
  			String orderitemPskuNameDetail=mlfrontOrderItem.getOrderitemPskuName();
//  			if(name.length()>40){
//  				name= name.substring(0, 40);//准备改改这里
  				name=name+"("+orderitemPskuNameDetail+")"+".";
//  			}
  			Integer skuNum=mlfrontOrderItem.getOrderitemPskuNumber();
  			String skuNumStr = skuNum+"";
  			String money = mlfrontOrderItem.getOrderitemPskuReamoney();
  			String oneMoney = getOnemoney(skuNum,money);
//  		demo:	item.setName(name).setQuantity("1").setCurrency("USD").setPrice(money);
  			item.setName(name).setQuantity(skuNumStr).setCurrency("USD").setPrice(oneMoney);
  			money = getOneAllMoney(skuNum,oneMoney);
  			subMoney = money;
  			List<Item> items = new ArrayList<Item>();
  			items.add(item);
  			itemList.setItems(items);
  		}
  	  	//地址参数
//        ShippingAddress shippingAddress = getShippingAddress(mlfrontAddress);
//  		ShippingAddress shippingAddress = getShippingAddressSDK(mlfrontAddress);
//        itemList.setShippingAddress(shippingAddress); 		
  		
  		Details details = new Details();
  		details.setShipping("0");
  		details.setSubtotal(subMoney);
  		details.setTax("0");//税
  		String shopdiscountMoney = "-"+Shopdiscount;
  		details.setShippingDiscount(shopdiscountMoney);
//  		details.setShipping((addressMoney));

  		// ###Amount
  		// Let's you specify a payment amount.
  		Amount amount = new Amount();
  		amount.setCurrency("USD");
  		// Total must be equal to sum of shipping, tax and subtotal.
  		
  		String amTotal = getamountTotal(subMoney,Shopdiscount,addressMoney);
  		amount.setTotal(amTotal);
  		amount.setDetails(details);
  		transaction.setAmount(amount);
  		transaction.setItemList(itemList);
  		
  		
  		// The Payment creation API requires a list of
  		// Transaction; add the created `Transaction`
  		// to a List
  		List<Transaction> transactions = new ArrayList<Transaction>();
  		transactions.add(transaction);

  		// ###Payer
  		// A resource representing a Payer that funds a payment
  		// Payment Method
  		// as 'paypal'
  		Payer payer = new Payer();
  		payer.setPaymentMethod("paypal");

  		// ###Payment
  		// A Payment Resource; create one using
  		// the above types and intent as 'sale'
  		Payment payment = new Payment();
  		payment.setIntent("sale");
  		payment.setPayer(payer);
  		payment.setTransactions(transactions);
  		System.out.println(payment.getTransactions().get(0).getAmount().toJSON());
  		
  		RedirectUrls redirectUrls = new RedirectUrls();
  		redirectUrls.setCancelUrl(cancelUrl);
  		redirectUrls.setReturnUrl(successUrl);
  		payment.setRedirectUrls(redirectUrls);
  		
  		System.out.println("paymentcreate,before");
  		System.out.println(payment.toJSON());
  		System.out.println("paymentcreate,after");

        return payment.create(apiContext);
    }

}
