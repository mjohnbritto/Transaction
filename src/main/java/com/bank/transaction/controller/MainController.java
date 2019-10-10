package com.bank.transaction.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transaction.model.TransactionDetails;

@RestController
@RequestMapping(value = "/api/v1/")
public class MainController {
	private static List<TransactionDetails> listTransactions = new ArrayList<TransactionDetails>();

	@PostMapping(value = "/bank-transaction")
	public Object saveTransaction(@RequestBody List<TransactionDetails> requstData) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss:sss");

			for (TransactionDetails transactionDetails : requstData) {
				Date tDate = formatter.parse(transactionDetails.getDate());
			}
			for (TransactionDetails transactionDetails : requstData) {
				listTransactions.add(transactionDetails);
			}

			for (TransactionDetails transactionDetails : listTransactions) {
				System.out.println(transactionDetails.getDate() + "==>" + transactionDetails.getAmount());
			}
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Transactions saved", HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/bank-transaction")
	public Object getTransaction() {
		double min = -1;
		double max = 0;
		double sum = 0;
		double avg = 0;
		long count = 0;
		JSONObject responeObject = new JSONObject();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss:sss");
			Date tDate;
			Date curDate;
			long diffInMillis;
			for (TransactionDetails transactionDetails : listTransactions) {
				tDate = formatter.parse(transactionDetails.getDate());
				curDate = new Date();
				diffInMillis = curDate.getTime() - tDate.getTime();

				if (diffInMillis < (1000 * 60 * 60)) {// 1 hour
					if (min < 0) {
						min = transactionDetails.getAmount();
					}
					if (transactionDetails.getAmount() < min) {
						min = transactionDetails.getAmount();
					}
					if (transactionDetails.getAmount() > max) {
						max = transactionDetails.getAmount();
					}
					sum += transactionDetails.getAmount();
					count++;
				}
			}
			avg = sum / count;

			responeObject.put("min", min);
			responeObject.put("max", max);
			responeObject.put("sum", sum);
			responeObject.put("average", avg);
			responeObject.put("count", count);

		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(responeObject.toString(), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/bank-transaction")
	public Object deleteTransaction() {
		try {
			listTransactions.removeAll(listTransactions);
		} catch (Exception exception) {
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Transactions deleted", HttpStatus.ACCEPTED);
	}

}
