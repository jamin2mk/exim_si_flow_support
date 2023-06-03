package com.test.module.compare;

import com.consts.gendoc.Service;
import com.helper.TestHelper;

public class Test_Services_GenDoc_Compare_Trangnmt {

	public static void main(String[] args) throws Exception {

		String service = Service.BM122;

		switch (service) {

		case Service.DXGN_BM35:
			TestHelper.compareGendocResult("resources/20230605/BM35");
			break;

		case Service.BM122:
			TestHelper.compareGendocResult("resources/20230605/BM122");
			break;

		case Service.DXGN_BM128:
			TestHelper.compareGendocResult("resources/20230605/BM128");
			break;

		case Service.BM129:

			break;

		case Service.BM124:

			break;

		case Service.DXGN_BM153:

			break;

		case Service.DXGN_BM148:

			break;

		case Service.DXGN_BM149:

			break;

		case Service.DXGN_BM150:

			break;

		case Service.DXGN_BM151:

			break;

		case Service.DXGN_BM152:

			break;

		case Service.DXGN_BM130:

			break;

		case Service.BM39:

			break;

		case Service.BM40:

			break;

		case Service.BM41:

			break;

		case Service.BM42:

			break;

		case Service.BM43:

			break;

		case Service.BM44:

			break;

		case Service.BM45:

			break;

		case Service.BM46:

			break;

		case Service.BM47:

			break;

		case Service.BM49:

			break;

		case Service.BM10:

			break;

		case Service.BM11:

			break;

		case Service.BM12:

			break;

		case Service.BM13:

			break;

		case Service.BM17:

			break;

		case Service.BM18:

			break;

		case Service.BM19:

			break;

		case Service.BM20:

			break;

		case Service.BM21:

			break;

		case Service.BM22:

			break;

		case Service.BM74:

			break;

		default:
			throw new Exception("Invalid service");
		}
	}

}
