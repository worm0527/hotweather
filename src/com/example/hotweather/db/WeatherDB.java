package com.example.hotweather.db;

import java.util.ArrayList;
import java.util.List;

import com.example.hotweather.model.City;
import com.example.hotweather.model.County;
import com.example.hotweather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 用于数据的存储、查询
 * 
 * @author 0527
 * 
 */
public class WeatherDB {
	/**
	 * 数据库名
	 */
	private static final String DB_NAME = "hot_weather";

	/**
	 * 数据库版本
	 */
	private static final int VERSION = 1;

	private static WeatherDB weatherDB;

	private SQLiteDatabase db;

	/**
	 * 构造函数私有化，采用单例模式
	 */
	private WeatherDB(Context context) {
		WeatherOpenHelper helper = new WeatherOpenHelper(context, DB_NAME,
				null, VERSION);
		db = helper.getWritableDatabase();
	}

	/**
	 * 获取WeatherDB实例
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized WeatherDB getInstance(Context context) {
		if (weatherDB == null) {
			weatherDB = new WeatherDB(context);
		}
		return weatherDB;
	}

	/**
	 * 将province实例存到表中
	 * 
	 * @param province
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_py", province.getProvincePy());
			db.insert("Province", null, values);
		}
	}

	/**
	 * 将city实例保存到表中
	 * 
	 * @param city
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_py", city.getCityPy());
			values.put("province_py", city.getProvincePy());
			db.insert("City", null, values);
		}
	}

	/**
	 * 将county实例保存到数据库
	 * 
	 * @param county
	 */
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("ciyt_name", county.getCountyName());
			values.put("county_py", county.getCountyPy());
			values.put("city_py", county.getCityPy());
			db.insert("County", null, values);
		}
	}

	/**
	 * 从数据库中读取所有省份信息
	 * 
	 * @return
	 */
	public List<Province> loadProvinces() {
		List<Province> provinces = new ArrayList<Province>();
		Province province = null;
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvincePy(cursor.getString(cursor
						.getColumnIndex("province_py")));
				provinces.add(province);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return provinces;
	}

	/**
	 * 获取某一省份下的所有城市
	 * 
	 * @param provincePy
	 *            省份拼音
	 * @return
	 */
	public List<City> loadCities(String provincePy) {
		List<City> cities = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_py = ?",
				new String[] { provincePy }, null, null, null);
		City city = null;
		if (cursor.moveToFirst()) {
			do {
				city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityPy(cursor.getString(cursor
						.getColumnIndex("city_py")));
				city.setProvincePy(cursor.getString(cursor
						.getColumnIndex("province_py")));
				cities.add(city);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return cities;
	}

	/**
	 * 获取某一城市下的所以区县
	 * 
	 * @param cityPy
	 *            城市拼音
	 * @return
	 */
	public List<County> loadCounties(String cityPy) {
		List<County> counties = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_py = ?",
				new String[] { cityPy }, null, null, null);
		County county = null;
		if (cursor.moveToFirst()) {
			do {
				county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyPy(cursor.getString(cursor.getColumnIndex("county_py")));
				county.setCityPy(cursor.getString(cursor.getColumnIndex("city_py")));
				counties.add(county);
			} while (cursor.moveToNext());
		}
		if (cursor != null) {
			cursor.close();
		}
		return counties;
	}
}
