package util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Teste {

	public static void main(String[] args) {
		try {
			Date data = DataUtil.converterStringParaDataComHora("2016-05-10T15:20");
			System.out.println(data);
			
			System.out.println(DataUtil.converterDataComHoraParaString(data));
			
			System.out.println(DataUtil.converterDataParaString(data));
			
			System.out.println(DataUtil.converterDataComHoraParaFormatoJSP(data));
			
			System.out.println(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(2.0));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
