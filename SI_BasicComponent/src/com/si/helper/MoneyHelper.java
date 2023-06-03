package com.si.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class MoneyHelper {
	public static void main(String[] args) {
//		 System.out.println(readMoneyToText("7013981812"));
		// System.out.println(readMoneyToText("1"));
		// System.out.println(readMoneyToText("100200"));
//		 System.out.println(readMoneyToText("7103009210"));
		 System.out.println(readMoneyToText("101592"));
		// System.out.println(FomartMoney("12121212"));
//		 System.out.println(FomartMoney2("11817839.5"));
//		 System.out.println(readMoneyToText("7103009210"));
//		System.out.println(readMoneyToText("12"));
	}


	public static String readMoneyToText(String moneyText) {
		String result = "";
		if (moneyText != null && !moneyText.equalsIgnoreCase("")) {

			String chuSo[] = { " không", " một", " hai", " ba", " bốn", " năm",
					" sáu", " bảy", " tám", " chín" };
			String tien[] = { "", " nghìn", " triệu", " tỷ", " nghìn tỷ",
					" triệu tỷ" };
			BigDecimal money = new BigDecimal(moneyText);
			int lan, i = 0;
			Double so = 0.0;
			String temp = "";
			boolean isFirst_Num = true;

			ArrayList<Number> viTri = new ArrayList<Number>();
			viTri.add(0, 0);
			viTri.add(1, 0);
			viTri.add(2, 0);
			viTri.add(3, 0);
			viTri.add(4, 0);
			viTri.add(5, 0);

			if (money.doubleValue() < 0) {
				return result = "Số tiền âm";
			}
			if (money.doubleValue() == 0) {
				return result = "Không";
			}
			if (money.doubleValue() > 0) {
				so = money.doubleValue();
			} else {
				so = -money.doubleValue();

			}
			if (money.doubleValue() > 8999999999999999D) {
				return result = "Số quá lớn";
			}

			viTri.set(5, Math.floor(so / 1000000000000000D));

			if (Double.isNaN(viTri.get(5).doubleValue())) {
				viTri.set(5, 0);
			}

			so = (so - Float.parseFloat(viTri.get(5).toString()) * 1000000000000000D);
			viTri.set(4, Math.floor(so / 1000000000000D));

			if (Double.isNaN(viTri.get(4).doubleValue())) {
				viTri.set(4, 0);
			}
			so = (so - Float.parseFloat(viTri.get(4).toString()) * 1000000000000D);
			viTri.set(3, Math.floor(so / 1000000000D));

			if (Double.isNaN(viTri.get(3).doubleValue())) {
				viTri.set(3, 0);
			}
			so = (so - (viTri.get(3).doubleValue() * 1000000000));
			viTri.set(2, (int) (so / 1000000));

			if (Double.isNaN(viTri.get(2).doubleValue())) {
				viTri.set(2, 0);

			}
			viTri.set(1, (so % 1000000) / 1000);
			if (Double.isNaN(viTri.get(1).doubleValue())) {
				viTri.set(1, 0);

			}
			viTri.set(0, so % 1000);

			if (Double.isNaN((viTri.get(0).doubleValue()))) {
				viTri.set(0, 0);
			}

			if (viTri.get(5).doubleValue() > 0) {
				lan = 5;
			} else if (viTri.get(4).doubleValue() > 0) {
				lan = 4;
			} else if (viTri.get(3).doubleValue() > 0) {
				lan = 3;
			} else if (viTri.get(2).doubleValue() > 0) {
				lan = 2;
			} else if (viTri.get(1).doubleValue() > 0) {
				lan = 1;
			} else {
				lan = 0;
			}
			for (i = lan; i >= 0; i--) {
				boolean Value_zero = false;

				if (viTri.get(i).intValue() < 100 && money.doubleValue() > 100) { // tiền
																					// phải
																					// >
																					// 100
					Value_zero = true; // Value_zero check có tồn tại số 0 trong
										// cặp số : 013, 102...
				}
				temp = read3Num(viTri.get(i).intValue(), chuSo, tien,
						Value_zero, isFirst_Num);
				result += temp;
				if (viTri.get(i).intValue() > 0) {
					result += tien[i];
				}

				if ((i > 0) && (temp.length() > 0)) {
					result += ",";
				}
				isFirst_Num = false;
			}
			if (result.substring(result.length() - 1).equalsIgnoreCase(",")) {
				result = result.substring(0, result.length() - 1);
			}
			result = result.substring(1, 2).toUpperCase() + result.substring(2);
		}
		return result;
	}

	private static String read3Num(int baso, String chuSo[], String tien[],
			boolean Value_zero, boolean isFirst_Num) {

		int tram = 0;
		int chuc = 0;
		int donvi = 0;

		String result = "";

		tram = baso / 100;
		chuc = (int) (baso % 100) / 10;
		donvi = baso % 10;

		if (tram == 0 && chuc == 0 && donvi == 0) {
			return result;
		}
		if (tram != 0) {
			result += chuSo[tram] + " trăm";
			if ((chuc == 0) && (donvi != 0)) {
				result += " linh";
			}
		}
		if (tram == 0 && Value_zero && !isFirst_Num) {
			if (baso >= 10) {
				result += chuSo[tram] + " trăm";
			}
			if (baso < 10) {
				result += chuSo[tram] + " trăm";
				if ((chuc == 0) && (donvi != 0)) {
					result += " linh";
				}
			}
		}
		if ((chuc != 0) && (chuc != 1)) {
			result += chuSo[chuc] + " mươi";
			if ((chuc == 0) && (donvi != 0)) {
				result += " linh";
			}
		}
		if (chuc == 1) {
			result += " mười";
		}
		switch (donvi) {
		case 1: {
			if ((chuc != 0) && (chuc != 1)) {
				result += " mốt";
			} else {
				result += chuSo[donvi];
			}
			break;

		}
		case 5: {
			if (chuc == 0) {
				result += chuSo[donvi];
			} else {
				result += " lăm";
			}
			break;
		}
		default:
			if (donvi != 0) {
				result += chuSo[donvi];
			}
			break;
		}
		// System.out.println(result);
		return result;
	}

	public static String FomartMoney(String money) {

		DecimalFormat myFm = new DecimalFormat("###,###.###");

		BigDecimal valideMoeny = new BigDecimal(money);

		String result = myFm.format(valideMoeny);

		return result;
	}

	public static String FomartMoney2(String money) {
		String result = "";
		if (money != null) {
			if (money.contains(",")) {
				result = money.replace(",", ".");
			} else if (money.contains(".")) {
				result = money;
			} else {
				final String regex = "\\B(?=(\\d{3})+(?!\\d))";

				result = money.replaceAll(regex, ".");
			}
		}
		return result;
	}
}
