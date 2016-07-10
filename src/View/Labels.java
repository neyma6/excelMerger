package View;

public class Labels 
{
	public static final String APP_TITLE = "Free Sector - Excel Merger";
	public static final String SUMMERY_TITLE = "Végeredmény";
	public static final String MENU_FILE = "File";
	public static final String MENU_EXIT = "Kilépés";
	public static final String FC_OPEN_FILE = "Megnyitás";
	public static final String FC_OPEN_FOLDER = "Megnyitás";
	public static final String FC_SIMILAR_INFO = "Kérlek válaszd ki a hasonló kódokat tartalmazó file-t:";
	public static final String FC_REFERENCE_INFO = "Kérlek válaszd ki a referencia file-t:";
	public static final String FC_FOLDER_INFO = "Kérlek válaszd ki a mappát:";
	public static final String FO_FILE = "File:";
	public static final String FO_ID = "Gyári kód oszlopa:";
	public static final String FO_DESC = "Leírás oszlopa:";
	public static final String FO_PRICE = "Ár oszlopa:";
	public static final String MW_START = "Start";
	public static final String SUMMERY_SAVE = "Mentés másként";
	public static final String DIALOG_WARNING_TITLE = "Figyelem";
	public static final String DIALOG_WARNING_MESSAGE = "Kérlek ne válaszd ki ugyanazokat az indexeket!";
	public static final String DIALOG_WARNING_FILL = "Kérlek válaszd ki a megfelelő file-okat!";
	public static final String DIALOG_ERROR_TITLE = "Hiba";
	public static final String DIALOG_ERROR_MESSAGE_IO = "Nem tudtam beolvasni az egyik file-t!";
	public static final String DIALOG_ERROR_MESSAGE_INVALID_FORMAT = "Az egyik file-nak nem megfelelő a formátuma!";
	public static final String DIALOG_ERROR_MESSAGE_SOMETHING = "Valami nem sikerült!";
	public static final String EXCEPTION_CANNOT_READ = "Nem sikerült beolvasni a következő file-t: ";
	public static final String EXCEPTION_WRITE_READ = "Nem lehet írni a file-t! Lehet, hogy egy másik program is használja!";
	public static final String DIALOG_WARNING_EXISTS_TITLE = "Létező file";
	public static final String DIALOG_WARNING_EXISTS_MESSAGE = "A file már létezik, felülírod?";
	public static final String DIALOG_SUCCESS_WRITE_TITLE = "Siker";
	public static final String DIALOG_SUCCESS_WRITE_MESSAGE = "A file írása megtörtént!";
	public static final String SPREAD = "Árrés";
	public static final String VAT = "ÁFA";
	
	//log
	public static final String LOG_REFERENCE_SIZE = "%d referenciát olvastam be (duplikáció nélkül)";
	public static final String LOG_SIMILAR_SIZE = "%d hasonló termékkódot olvastam be";
	public static final String LOG_SPREAD = "Árrés: %f";
	public static final String LOG_VAT = "ÁFA: %f";
	public static final String LOG_FILE_READED = "%s file sikeresen beolvasva";
	public static final String LOG_COLUMN_NUMBERS = "A következő oszlopokat olvastam be\n ...gyári kód: %d\n ...leírás: %d\n ...ár: %d";
	public static final String LOG_PRODUCT_NUMBERS = "Beolvasott áruk száma: %d\n";
	public static final String LOG_AFTER_MERGER = "Egyesítés után az áruk száma: %d\n";
	public static final String LOG_ELAPSED_SECONDS = "A program futásának ideje: %d másodperc";
}
