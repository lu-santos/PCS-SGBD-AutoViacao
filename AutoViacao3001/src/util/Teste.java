package util;

import java.text.ParseException;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {
		try {
			Date data = DataUtil.converterStringParaDataComHora("2016-05-10T15:20");
			System.out.println(data);
			
			System.out.println(DataUtil.converterDataComHoraParaString(data));
			
			System.out.println(DataUtil.converterDataParaString(data));
			
			System.out.println(DataUtil.converterDataComHoraParaFormatoJSP(data));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
