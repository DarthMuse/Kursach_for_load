package com.example.kursachv11;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SettingOtherTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_other_time);
    }

    static boolean chisloW = false, chisloG = false;
    EditText timeWakeUp, timeGo, howManyWake, howManyOut, intervalAlarmG, intervalAlarmW;
    int HowWake = 2, HowOut = 1;
    int intervalW = 5, intervalG = 5;
    int hTimeWake = 0,  mTimeWake = 30;
    int hTimeG = 1, mTimeG = 0;

    public void learn (View v)
    {
        timeWakeUp = (EditText) findViewById(R.id.timeToWakeUp);
        timeGo = (EditText) findViewById(R.id.timeToGoInUniversity);
        howManyWake = (EditText) findViewById(R.id.howManyToWakeUp);
        howManyOut = (EditText) findViewById(R.id.howManyToGoOut);
        intervalAlarmW = (EditText) findViewById(R.id.intervalAlarmW);
        intervalAlarmG = (EditText) findViewById(R.id.intervalAlarmG);

        if (timeWakeUp.getText().toString().length() != 0)
        {
            try
            {
                LongWG(Integer.parseInt(timeWakeUp.getText().toString()), 155, -530, false);
            }
            catch (NumberFormatException e)
            {
                InputNumber(155, -530, "Введите число");
            }
        }
        else
        {
            InputNumber(155, -530, "Введите число (время на проснутся)");
        }

        if (timeGo.getText().toString().length() != 0)
        {
            try
            {
                LongWG(Integer.parseInt(timeGo.getText().toString()), 200, -420, true);
            }
            catch (NumberFormatException e)
            {
                InputNumber(200, -420, "Введите число");
            }
        }
        else
        {
            InputNumber(200, -420, "Введите число (время на путь)");
        }

        if(howManyWake.getText().toString().length() != 0)
        {
            try
            {
                HowMany(Integer.parseInt(howManyWake.getText().toString()), -100, -210, true);
            }
            catch (NumberFormatException e)
            {
                InputNumber(-100, -210, "Введите число");
            }
        }

        if(howManyOut.getText().toString().length() != 0)
        {
            try
            {
                HowMany(Integer.parseInt(howManyOut.getText().toString()), -100, -40, false);
            }
            catch (NumberFormatException e)
            {
                InputNumber(-100, -40, "Введите число");
            }
        }

        if(intervalAlarmW.getText().toString().length() != 0)
        {
            try
            {
            Interval(Integer.parseInt(intervalAlarmW.getText().toString()), 110, 130, true);
            }
            catch (NumberFormatException e)
            {
                InputNumber(110, 130, "Введите число");
            }
        }

        if(intervalAlarmG.getText().toString().length() != 0)
        {
            try
            {
                Interval(Integer.parseInt(intervalAlarmG.getText().toString()), 110, 320, false);
            }
            catch (NumberFormatException e)
            {
                InputNumber(110, 320, "Введите число");
            }
        }

        TimeFirstLesson.howManyWake = HowWake;
        TimeFirstLesson.howManyOut  = HowOut;
        TimeFirstLesson.timeWakeUpH = hTimeWake;
        TimeFirstLesson.timeWakeUpM = mTimeWake;
        TimeFirstLesson.timeGoH = hTimeG;
        TimeFirstLesson.timeGoM = mTimeG;
        TimeFirstLesson.chisloG = chisloG;
        TimeFirstLesson.chisloW = chisloW;
        TimeFirstLesson.timeIntervalW = intervalW;
        TimeFirstLesson.timeIntervalG = intervalG;

        MgtuBaumanka.howManyWake = HowWake;
        MgtuBaumanka.howManyOut  = HowOut;
        MgtuBaumanka.timeWakeUpH = hTimeWake;
        MgtuBaumanka.timeWakeUpM = mTimeWake;
        MgtuBaumanka.timeGoH = hTimeG;
        MgtuBaumanka.timeGoM = mTimeG;
        MgtuBaumanka.chisloG = chisloG;
        MgtuBaumanka.chisloW = chisloW;
        MgtuBaumanka.timeIntervalW = intervalW;
        MgtuBaumanka.timeIntervalG = intervalG;

        Toast.makeText(this, "Корректная информация записана", Toast.LENGTH_SHORT).show();
    }

    public void LongWG (int time, int x, int y, boolean WaGo)
    {
        if (time > 0)
        {
            if (time % 100 < 60)
            {
                if (time / 100 < 4)
                {
                    if (WaGo == true)
                    {
                        hTimeG = time / 100;
                        mTimeG = time % 100;
                        chisloW = true;
                    }
                    else
                    {
                        hTimeWake = time / 100;
                        mTimeWake = time % 100;
                        chisloG = true;
                    }
                }
                else
                {
                    if (WaGo == true)
                    {
                        InputNumber(x, y, "Введите время менее 4-ех часов");
                    }
                    else
                    {
                        InputNumber(x, y, "Введите время менее 4-ех часов");
                    }
                }
            }
            else
            {
                if (WaGo == true)
                {
                    InputNumber(x, y, "Число не соответствует временным размерностям");
                }
                else
                {
                    InputNumber(x, y, "Число не соответствует временным размерностям");
                }
            }
        }
        else
        {
            if (WaGo == true)
            {
                InputNumber(x, y, "Введите положительное число");
            }
            else
            {
                InputNumber(x, y, "Введите положительное число");
            }
        }
    }

    public void HowMany (int Number, int x, int y, boolean WO)
    {
        if(Number > 0)
        {
            if(Number < 10)
            {
                if (WO == true)
                {
                    HowWake = Number;
                    Log.i("LearnTime", HowWake + " Время");
                }
                else
                {
                    HowOut = Number;
                    Log.i("LearnTime", HowOut + " Время");
                }
            }
            else
            {
                InputNumber(x, y, "Введите число не более чем 10");
            }
        }
        else
        {
            InputNumber(x, y, "Введите число > 0");
        }
    }

    public void Interval (int Number, int x, int y, boolean WO)
    {
        if(Number > 0)
        {
            if(Number < 20)
            {
                if (WO == true)
                {
                    intervalW = Number;
                    Log.i("LearnTime", HowWake + " Время");
                }
                else
                {
                    intervalG = Number;
                    Log.i("LearnTime", HowOut + " Время");
                }
            }
            else
            {
                InputNumber(x, y, "Введите время меньше, чем 20 минут");
            }
        }
        else
        {
            InputNumber(x, y, "Введите время не менее 1 минуты");
        }
    }

    public void InputNumber(int x, int y, String s)
    {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    public void onButtonClickTimeFirstLessonWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(SettingOtherTime.this, TimeFirstLesson.class);
        startActivity(intent);
    }

    public void onButtonClickmgtuLessonWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(SettingOtherTime.this, MgtuBaumanka.class);
        startActivity(intent);
    }

    public void onButtonClickMainActivityWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(SettingOtherTime.this, MainActivity.class);
        startActivity(intent);
    }
}