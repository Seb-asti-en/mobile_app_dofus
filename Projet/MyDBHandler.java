import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {

	//information of database

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "studentDB.db";
	public static final String TABLE_NAME = "Student";
	public static final String COLUMN_ID = "StudentID";
	public static final String COLUMN_NAME = "StudentName";

//initialize the database

	public MyDBHandler(Context context, Stringname DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID +
		"INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

	public String loadHandler() {
		String result = "";
		String query = "Select*FROM" + TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		while (cursor.moveToNext()) {
			int result_0 = cursor.getInt(0);
			String result_1 = cursor.getString(1);
			result += String.valueOf(result_0) + " " + result_1 +
			System.getProperty("line.separator");
		}
		cursor.close();
		db.close();
		return result;
	}

	public void addHandler(Student student) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, student.getID());
		values.put(COLUMN_NAME, student.getStudentName());
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public Student findHandler(String studentname) {
		Stringquery = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + studentname + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Student student = new Student();
		if (cursor.moveToFirst()) {
			cursor.moveToFirst();
			student.setID(Integer.parseInt(cursor.getString(0)));
			student.setStudentName(cursor.getString(1));
			cursor.close();
		} else {
			student = null;
		}
		db.close();
		return student;
	}

	public boolean deleteHandler(int ID) {

		booleanresult = false;
		Stringquery = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Student student = new Student();

		if (cursor.moveToFirst()) {
			student.setID(Integer.parseInt(cursor.getString(0)));
			db.delete(TABLE_NAME, COLUMN_ID + "=?",
			newString[] {
				String.valueOf(student.getID())
			});
			cursor.close();
			result = true;
		}

		db.close();

		return result;

	}

	public boolean updateHandler(int ID, String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues args = new ContentValues();
		args.put(COLUMN_ID, ID);
		args.put(COLUMN_NAME, name);
		return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
	}

}