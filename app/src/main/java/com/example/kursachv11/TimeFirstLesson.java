package com.example.kursachv11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeFirstLesson extends AppCompatActivity {
    EditText timeMonday, timeTuesday, timeWednesday, timeThursday, timeFriday, timeSaturday;
    public static int timeWakeUpH, timeWakeUpM, timeGoH, timeGoM, howManyWake, howManyOut, timeIntervalW, timeIntervalG;
    public static boolean chisloW, chisloG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_first_lesson);
    }

    public void CreateTime(View v) {
        timeMonday = (EditText) findViewById(R.id.timeInMonday);
        timeTuesday = (EditText) findViewById(R.id.timeInTuesday);
        timeWednesday = (EditText) findViewById(R.id.timeInWednesday);
        timeThursday = (EditText) findViewById(R.id.timeInThursday);
        timeFriday = (EditText) findViewById(R.id.timeInFriday);
        timeSaturday = (EditText) findViewById(R.id.timeInSaturday);

        int longTextM = timeMonday.getText().toString().length();
        int longTextTues = timeTuesday.getText().toString().length();
        int longTextWen = timeWednesday.getText().toString().length();
        int longTextThur = timeThursday.getText().toString().length();
        int longTextF = timeFriday.getText().toString().length();
        int longTextS = timeSaturday.getText().toString().length();

        if ((chisloW == true) && (chisloG == true)) {
            if ((longTextM != 0) || (longTextTues != 0) || (longTextWen != 0) || (longTextThur != 0) || (longTextF != 0) || (longTextS != 0)) {
                if ((longTextM != 0)) {
                    try {
                        ArrayList<Integer> alarmDaysM = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.MONDAY,
                                Integer.parseInt(timeMonday.getText().toString()), 0, -490, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysM);
                    } catch (NumberFormatException e) {
                        InputNumber(0, -490, "Введите число");
                    }
                }

                if (longTextTues != 0) {
                    try {
                        ArrayList<Integer> alarmDaysTu = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.TUESDAY,
                                Integer.parseInt(timeTuesday.getText().toString()), 0, -380, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysTu);
                    } catch (NumberFormatException e) {
                        InputNumber(0, -380, "Введите число");
                    }
                }

                if (longTextWen != 0) {
                    try {
                        ArrayList<Integer> alarmDaysW = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.WEDNESDAY,
                                Integer.parseInt(timeWednesday.getText().toString()), 0, -270, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysW);
                    } catch (NumberFormatException e) {
                        InputNumber(-140, -270, "Введите число");
                    }
                }

                if (longTextThur != 0) {
                    try {
                        ArrayList<Integer> alarmDaysTh = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.THURSDAY,
                                Integer.parseInt(timeTuesday.getText().toString()), 0, -160, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysTh);
                    } catch (NumberFormatException e) {
                        InputNumber(0, -160, "Введите число");
                    }
                }

                if (longTextF != 0) {
                    try {
                        ArrayList<Integer> alarmDaysF = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.FRIDAY,
                                Integer.parseInt(timeFriday.getText().toString()), 0, -34, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysF);
                    } catch (NumberFormatException e) {
                        InputNumber(0, -34, "Введите число");
                    }
                }

                if (longTextS != 0) {
                    try {
                        ArrayList<Integer> alarmDaysS = new ArrayList<>();
                        AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.SATURDAY,
                                Integer.parseInt(timeSaturday.getText().toString()), 0, 80, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysS);
                    } catch (NumberFormatException e) {
                        InputNumber(0, 80, "Введите число");
                    }
                }
            } else {
                InputNumber(0, 370, "Не введено время ни одной пары");
            }
        } else {
            InputNumber(0, 370, "Не указано доп. время (на подъем и на сборы)");
        }
    }

    public void AlarmCreate(int hTimeG, int mTimeG, int hTimeWake, int mTimeWake, int dayOfWeek, int time, int x, int y, int howWake, int howOut, int intervalW, int intervalG, ArrayList massivAlarm) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

        int hTime = time / 100;
        int mTime = time % 100;

        if (hTime < 24 && mTime < 60) //если минуты и часы введены корректно
        {
            if (hTime >= 8)
            {
                hTime -= hTimeG; //вычитаем часы на путь

                if (mTime - mTimeG < 0) //если при вычитании минут на пусть получается минут
                {
                    hTime--; //получается сдвинулся час
                    mTime = 60 + mTime - mTimeG; //и выставляем правильные минуты
                } else {
                    mTime -= mTimeG;
                }

                int count = 0;

                while (howOut != 0) {
                    //тавим будильник на выход
                    intent.putExtra(AlarmClock.EXTRA_HOUR, hTime);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, mTime);
                    massivAlarm.add(dayOfWeek);
                    intent.putExtra(AlarmClock.EXTRA_DAYS, massivAlarm);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Выход");
                    startActivity(intent);

                    if (mTime + intervalG == 60) {
                        hTime++;
                        mTime = 0;
                    } else if (mTime > 60) {
                        mTime -= 60;
                        hTime++;
                    } else {
                        mTime += intervalG;
                    }

                    count++;
                    howOut--;
                }

                if (count > 0) {
                    while (count != 0) {
                        mTime -= intervalG;
                        count--;
                    }
                }

                hTime -= hTimeWake; //вычитаем время на подъем

                if (mTime - mTimeWake < 0) {
                    hTime--;
                    mTime = 60 + mTime - mTimeWake;
                } else {
                    mTime -= mTimeWake;
                }

                while (howWake != 0) {
                    //первый будильник на встать
                    intent.putExtra(AlarmClock.EXTRA_HOUR, hTime);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, mTime);
                    massivAlarm.add(dayOfWeek);
                    intent.putExtra(AlarmClock.EXTRA_DAYS, massivAlarm);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Вставать");
                    startActivity(intent);

                    if (mTime + intervalW == 60) {
                        hTime++;
                        mTime = 0;
                    } else if (mTime > 60) {
                        mTime -= 60;
                        hTime++;
                    } else {
                        mTime += intervalW;
                    }
                    howWake--;
                }

                Toast.makeText(this, "Будильики созданы", Toast.LENGTH_LONG).show();
            }
            else
            {
                InputNumber(x, y, "Введите время не меньше 8 часов");
            }
        } else {
            InputNumber(0, 370, "Число не соответствует временным размерностям");
        }
    }


    public void InputNumber(int x, int y, String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    public void delete(View v)
    {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.ACTION_DISMISS_ALARM,"");
        startActivity(intent);
    }

    public void onButtonClickSettingOtherTimeWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(TimeFirstLesson.this, SettingOtherTime.class);
        startActivity(intent);
    }

    public void onButtonClickMainActivityWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(TimeFirstLesson.this, MainActivity.class);
        startActivity(intent);
    }

    public void onButtonClickmgtuWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(TimeFirstLesson.this, MgtuBaumanka.class);
        startActivity(intent);
    }
}