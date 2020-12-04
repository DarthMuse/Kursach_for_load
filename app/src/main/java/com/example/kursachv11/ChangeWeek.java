package com.example.kursachv11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ChangeWeek extends AppCompatActivity {

    EditText timeSunday; //поле класса текствого поля окна

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_week);
    }

    public void onButtonClickChangeWeek(View v)
    {
        MgtuBaumanka object = new MgtuBaumanka();
        object.setContext(this);

        //открытие данных и выгрузка всех ланных, тут время нужно знать какая сейчас неделя, то есть ЧС или ЗН
        object.sPref = getPreferences(MODE_PRIVATE); //получаем объект класса SharedPreferences (сохранения данных)
        String loadText = object.sPref.getString("info", ""); //взята информация, находящаяся в файле
        if (loadText.charAt(20) != 0)
        {
            object.nowWeek = Character.getNumericValue(loadText.charAt(20)); //взятие данных о текущей недели
        }

        //смена недели
        if (object.nowWeek == 1) //если знаменатель
        {
            object.nowWeek = 0; //то на числитель
            InputNumber(0, 460, "Неделя сменена на числитель");
            object.ChooseWeek();
        }
        else if (object.nowWeek == 0) //если числитель
        {
            object.nowWeek = 1; //то на знаменатель
            InputNumber(0, 460, "Неделя сменена на знаменатель");
            object.ChooseWeek();
        }
    }

    public void CreateTime(View v) //создание будильника напоминалки
    {
        timeSunday = (EditText) findViewById(R.id.timeInSunday);
        ArrayList<Integer> alarmDaysS = new ArrayList<>(); //массив будильников

        if(timeSunday.getText().toString().length() != 0) //если поле не пустое
        {
            try //проверка на символы
            {
                int time = Integer.parseInt(timeSunday.getText().toString()); //считывание времени из текстового поля
                if (time > 0)
                {
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM); //объект класса "намерения", создание будильника

                    int hTime = time / 100;
                    int mTime = time % 100;

                    if (hTime < 24 && mTime < 60) //если минуты и часы введены корректно
                    {
                        intent.putExtra(AlarmClock.EXTRA_HOUR, hTime);
                        intent.putExtra(AlarmClock.EXTRA_MINUTES, mTime);
                        alarmDaysS.add(Calendar.SUNDAY);
                        intent.putExtra(AlarmClock.EXTRA_DAYS, alarmDaysS);
                        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Поменять неделю");
                        startActivity(intent);
                    }
                    else
                    {
                        InputNumber(130, 140, "Число не соответствует временным размерностям");
                    }
                }
                else
                {
                    InputNumber(130, 140, "Введите положительное число");
                }
            }
            catch (NumberFormatException e)
            {
                InputNumber(130, 140, "Введите число");
            }
        }
        else
        {
            InputNumber(130, 140, "Поле для значения пустое");
        }
    }

    public void InputNumber(int x, int y, String s) //функция, выводящая всплывающие сообщения
    {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG); //создание объекта класса всплывающего уведомления
        toast.setGravity(Gravity.CENTER, x, y); //установка его места появления
        toast.show(); //активация, то есть появление всплывающего уведомления
    }

    public void onButtonClickMgtuLessonWindow (View v) //переход на сраницу с расписанием МГТУ
    {
        Intent intent = new Intent(ChangeWeek.this, MgtuBaumanka.class);
        startActivity(intent);
    }

    public void onButtonClickMainActivityWindow (View v) //переход на сраницу главного меню
    {
        Intent intent = new Intent(ChangeWeek.this, MainActivity.class);
        startActivity(intent);
    }
}