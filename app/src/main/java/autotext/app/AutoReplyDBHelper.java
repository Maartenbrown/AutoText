package autotext.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fey on 10/28/2016.
 */

public class AutoReplyDBHelper extends SQLiteOpenHelper {

    public static final String T1Name = "User";//User Table
    public static final String T1C1 = "name";
    public static final String T1C2 = "password";
    public static final String T1Key = "user_ID";
    private static final String CreateT1 ="CREATE TABLE "+T1Name+" ("+T1Key+" INTEGER PRIMARY KEY,"
            +T1C1+" VARCHAR(20) NOT NULL UNIQUE, "
            +T1C2 +" VARCHAR(255) NOT NULL);";
    public static final String T2Name = "Contact";//Contact Table
    public static final String T2Key = "cID";
    public static final String T2C1 = "name";
    public static final String T2C2 ="uID_User";
    private static final String CreateT2 = "CREATE TABLE "+T2Name+" ("+T2Key+" INTEGER PRIMARY KEY, "
            +T2C1+ " VARCHAR(50), "+T2C2+" INTEGER, FOREIGN KEY ("+T2C2+") REFERENCES "+T1Name+"("+T1Key+"));";
    public static final String T3Name="ReceivedMessages";//Received Messages Table
    public static final String T3Key ="rMessageID";
    public static final String T3C1 ="time";
    public static final String T3C2 ="messageText";
    public static final String T3C3 ="uID_Users";
    public static final String T3C4 = "cID_Contact";
    private static final String CreateT3 ="CREATE TABLE "+T3Name+" ("+T3Key+" INTEGER PRIMARY KEY, "+
            T3C1+" TEXT, "+
            T3C2+" VARCHAR, "+T3C3+" INTEGER, "+
            T3C4+" INTEGER, FOREIGN KEY ("+T3C3+") REFERENCES "+T1Name+"("+T1Key+"), FOREIGN KEY ("+T3C4+") REFERENCES "+T2Name+"("+T2Key+"));";
    public static final String T4Name="SentMessages";//Sent Messages Table
    public static final String T4Key ="sMessageID";
    public static final String T4C1 ="time";
    public static final String T4C2 ="messageText";
    public static final String T4C3 ="uID_Users";
    public static final String T4C4 = "cID_Contact";
    private static final String CreateT4 ="CREATE TABLE "+T4Name+" ("+T4Key+" INTEGER PRIMARY KEY, "+
            T4C1+" TEXT , "+
            T4C2+" VARCHAR,"+T4C3+" INTEGER, "+
            T4C4+" INTEGER, FOREIGN KEY ("+T4C3+") REFERENCES "+T1Name+"("+T1Key+"), FOREIGN KEY ("+T4C4+") REFERENCES "+T2Name+"("+T2Key+"));";
    public static final String T5Name ="PhoneContact";//Phone Contact Table
    public static final String T5Key ="phoneNumber";
    public static final String T5C1 ="cID_Contact";
    private static final String CreateT5 = "CREATE TABLE "+T5Name+" ("+T5Key+" INTEGER PRIMARY KEY, "+
            T5C1+" INTEGER, FOREIGN KEY ("+T5C1+") REFERENCES "+T2Name+"("+T2Key+"));";
    public static final String T6Name = "WiFiConditon";//Wifi Condition
    public static final String T6Key = "wiFiCondID";
    public static final String T6C1 = "wifiName";
    public static final String T6C2 = "leaveEnter"; //0 for enter, 1 for leave
    private static final String CreateT6="CREATE TABLE "+T6Name+" ("+T6Key+" INTEGER PRIMARY KEY, "+
            T6C1+" VARCHAR(255), "+T6C2+" INTEGER);";
    public static final String T7Name ="GPSCondition";//GPS Condition
    public static final String T7Key ="GPSID";
    public static final String T7C1 ="longitude";
    public static final String T7C2 = "latitude";
    public static final String T7C3 = "radius";
    public static final String T7C4 = "leaveEnter";
    private static final String CreateT7 ="CREATE TABLE "+T7Name+" ("+T7Key+" INTEGER PRIMARY KEY, "+
            T7C1+" REAL, "+T7C2+" REAL, "+T7C3+" REAL, "+T7C4+" INTEGER);";
    public static final String T8Name = "MessageCondition";//Message Condition
    public static final String T8Key = "condID";
    public static final String T8C1 ="wiFiID_WifiCond";
    public static final String T8C2 ="GPSID_gpsCond";
    private static final String CreateT8 ="CREATE TABLE "+T8Name+" ("+T8Key+" INTEGER PRIMARY KEY, "+
            T8C1+" INTEGER, "+
            T8C2+" INTEGER, FOREIGN KEY ("+T8C1+") REFERENCES "+T6Name+" ("+T6Key+"), FOREIGN KEY ("+T8C2+") REFERENCES "+T7Name+" ("+T7Key+"));";
    public static final String T9Name = "ProgMessage";
    public static final String T9Key = "pMessageID";
    public static final String T9C1 = "messText";
    public static final String T9C2= "timeStart";
    public static final String T9C3 ="timeEnd";
    public static final String T9C4 = "daysActive";
    public static final String T9C5 = "weeklyRepeat";//integer yes =0 no = 1
    public static final String T9C6 = "onOff"; //integer on = 0, off =1
    public static final String T9C7 = "condID_MessageCondition";
    public static final String T9C8 = "uID_User";
    private static final String CreateT9="CREATE TABLE "+T9Name +" ("+T9Key+" INTEGER PRIMARY KEY, "+
            T9C1+" VARCHAR , "+T9C2+" TEXT, "+T9C3+" TEXT, "+T9C4+" TEXT, "+T9C5+" INTEGER, "+
            T9C6+" INTEGER, "+T9C7+" INTEGER, "+
            T9C8+" INTEGER,  FOREIGN KEY ("+T9C7+") REFERENCES "+T8Name+" ("+T8Key+") FOREIGN KEY ("+T9C8+") REFERENCES "+T1Name+" ("+T1Key+"));";

    //DOES NOT CURRENTLY INCLUDE FACEBOOK OR TWITTER CONTACTS, should be new tables given how it's set up
    public static final String T10Name ="PhoneContactList";//Message Contact List Table
    public static final String T10C1 = "pMessageID_ProgMessage";
    public static final String T10C2 = "phoneNumber_PhoneContact";
    private static final String CreateT10 ="CREATE TABLE "+T10Name+" ( "+T10C1+" INTEGER, "+
            T10C2+" INTEGER, FOREIGN KEY ("+T10C1+") REFERENCES "+T9Name+" ("+T9Key+") ,FOREIGN KEY ("+T10C2+") REFERENCES "+T5Name+" ("+T5Key+"), "+
            "PRIMARY KEY ("+T10C1+", "+T10C2+"));";




    public AutoReplyDBHelper(Context context){

        super(context, DatabaseManager.DATABASE_NAME ,null, DatabaseManager.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateT1);
        db.execSQL(CreateT2);
        db.execSQL(CreateT3);
        db.execSQL(CreateT4);
        db.execSQL(CreateT5);
        db.execSQL(CreateT6);
        db.execSQL(CreateT7);
        db.execSQL(CreateT8);
        db.execSQL(CreateT9);
        db.execSQL(CreateT10);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
