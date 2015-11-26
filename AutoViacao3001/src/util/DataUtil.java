package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat DATA_COM_BARRAS = new SimpleDateFormat("dd/MM/yyyy");
	private static final DateFormat DATA_HORA_COM_BARRAS = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	/**
	 * Esta função recebe um java.util.Date e o retorna numa String com o seguinte formato:
	 * yyyy-MM-dd
	 * @param data
	 * @return String formatada
	 */
	public static String converterDataParaString(Date data){
		return DATE_FORMAT.format(data);
	}
	
	/**
	 * Esta função recebe um java.util.Date e o retorna numa String com o seguinte formato:
	 * yyyy-MM-dd HH:mm:ss
	 * @param data
	 * @return String formatada
	 */
	public static String converterDataComHoraParaString(Date data){
		if (data != null)
			return DATETIME_FORMAT.format(data);
		return "";
	}
	
	/**
	 * Esta função recebe uma String no formato fornecido pelo input do tipo datetime-local
	 * e retorna um java.util.Date contendo a data e a hora da String recebida como parâmetro.
	 * @param dataString
	 * @return	java.util.Date contendo a data e a hora da String recebida como parâmetro
	 * @throws ParseException
	 */
	public static Date converterStringParaDataComHora(String dataString) throws ParseException{
		String novaDataString = dataString;
		
		if (dataString.contains("T")){
			String[] vetor = dataString.split("T");
			StringBuilder builder = new StringBuilder();
			builder.append(vetor[0]);
			builder.append(" ");
			builder.append(vetor[1]);
			builder.append(":00");
			novaDataString = builder.toString();
		}
		
		return DATETIME_FORMAT.parse(novaDataString);
	}
	
	public static String converterDataComHoraParaFormatoJSP(Date data){
		return formatarData(data, "yyyy-MM-dd'T'HH:mm");
	}
	
	public static String formatarData(Date data){
		return DATA_COM_BARRAS.format(data);
	}
	
	public static String formatarData(Date data, String pattern){
		return new SimpleDateFormat(pattern).format(data);
	}
	
	public static String formatarDataHora (Date data){
		return DATA_HORA_COM_BARRAS.format(data);
	}

}
