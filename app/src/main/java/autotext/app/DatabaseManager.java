package autotext.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;

import java.sql.PreparedStatement;

import static autotext.app.AutoReplyDBHelper.*;


/**
 * Created by Fey on 10/28/2016.
 */

public class DatabaseManager {
    public	static	final	String	DATABASE_NAME	=	"UserDB";
    public	static	final	int	DATABASE_VERSION	=	1;
    private Context context;
    private SQLiteDatabase db;

    public DatabaseManager(Context context){
        this.context= context;
        AutoReplyDBHelper helper = new AutoReplyDBHelper(this.context);
        this.db = helper.getWritableDatabase();
    }
    public long checkUser(String user, String pass){
        String sql ="SELECT "+T1Key+" FROM "+T1Name+" WHERE "+T1C1+" = ? AND "+T1C2+" = ? ";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1, user);
        state.bindString(2, pass);
        long res=-1;
        try {
            res =state.simpleQueryForLong();
        }
        catch (SQLiteDoneException e){
            return res;
        }


        return res;
    }
    public long addUser(String user, String pass){
        String sql2 ="SELECT "+T1Key+" FROM "+T1Name+" WHERE "+T1C1+" = ?";
        SQLiteStatement test = db.compileStatement(sql2);
        test.bindString(1, user);
        if(test.simpleQueryForLong()>=0){
            return -1;
        }
        String sql = "INSERT INTO "+T1Name+"("+T1C1+", "+T1C2+") VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,user);
        state.bindString(2, pass);
        return state.executeInsert();
    }
    public long addContact(String name, int user){
        String sql = "INSERT INTO "+T2Name+"("+T2C1+", "+T2C2+") VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,name);
        state.bindLong(2, user);
        return state.executeInsert();
    }
    public long addReceivedMessage(String time, String messageText, int user, int contact){
        String sql = "INSERT INTO "+T3Name+"("+T3C1+", "+T3C2+", "+T3C3+", "+T3C4+") VALUES (?, ?, ?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,time);
        state.bindString(2, messageText);
        state.bindLong(3, user);
        state.bindLong(4, contact);
        return state.executeInsert();

    }
    public long addSentMessage(String time, String messageText, int user, int contact){
        String sql = "INSERT INTO "+T4Name+"("+T4C1+", "+T4C2+", "+T4C3+", "+T4C4+" VALUES) (?, ?, ?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,time);
        state.bindString(2, messageText);
        state.bindLong(3, user);
        state.bindLong(4, contact);
        return state.executeInsert();
    }
    public long addPhoneContact(int number, String name){
        String sql = "INSERT INTO "+T5Name+"("+T5Key+", "+T5C1+") VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindLong(1,number);
        state.bindString(2, name);
        return state.executeInsert();
    }
    public long addWiFiConition(String name, int leaveEnter){
        String sql = "INSERT INTO "+T6Name+"("+T6C1+", "+T6C2+") VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,name);
        state.bindLong(2, leaveEnter);
        return state.executeInsert();
    }
    public long addGPSCondition(double longi, double lat, double radius){
        String sql = "INSERT INTO "+T7Name+"("+T7C1+", "+T7C2+", "+T7C3+") VALUES (?, ?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindDouble(1, longi);
        state.bindDouble(2, lat);
        state.bindDouble(3, radius);
        return state.executeInsert();
    }
    public long addMessageCond(int wifiCond, int gpsCond){
        String sql = "INSERT INTO "+T8Name+"("+T8C1+", "+T8C2+") VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindLong(1,wifiCond);
        state.bindLong(2, gpsCond);
        return state.executeInsert();
    }
    public long addProgMessage(String text, String startTime, String endTime, String days, int repeat, int onOff, int condition, int user){
        String sql = "INSERT INTO " +T9Name+"("+T9C1+", "+T9C2+", "+T9C3+", "+T9C4+", "+T9C5+", "+T9C6+", "+T9C7+", "+T9C8+") VALUES (?,?,?,?,?,?,?,?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindString(1,text);
        state.bindString(2,startTime);
        state.bindString(3,endTime);
        state.bindString(4,days);
        state.bindLong(5, repeat);
        state.bindLong(6, onOff);
        state.bindLong(7, condition);
        state.bindLong(8, user);
        return state.executeInsert();

    }
    public long addPhoneContactList(int pMessage, int phoneContact){
        String sql = "INSERT INTO "+T10Name+"("+T10C1+", "+T10C2+" VALUES (?, ?)";
        SQLiteStatement state = db.compileStatement(sql);
        state.bindLong(1,pMessage);
        state.bindLong(2, phoneContact);
        return state.executeInsert();
    }
}
