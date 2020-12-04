package com.example.kursachv11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MgtuBaumanka extends AppCompatActivity {
    Switch sw1, sw2;
    EditText timeMondayMgtu, timeTuesdayMgtu, timeWednesdayMgtu, timeThursdayMgtu, timeFridayMgtu, timeSaturdayMgtu; //куда передаются данные из полей времени
    public static int timeWakeUpH, timeWakeUpM, timeGoH, timeGoM, howManyWake, howManyOut, timeIntervalW, timeIntervalG; //доп. время для будильников
    static int nowWeek = 2; //текущая неделя
    int timeMMgtu, timeTuMgtu, timeWMgtu, timeThMgtu, timeFMgtu, timeSMgtu; //время, которое передается уже именно в будильники
    int longTextMMgtu, longTextTuesMgtu, longTextWenMgtu, longTextThurMgtu, longTextFMgtu, longTextSMgtu; //введено ли время пар
    static int timeMCH = 0, timeTuesCH = 0, timeWCH = 0, timeThurCH = 0, timeFCH = 0, timeSCH = 0, timeMZN = 0, timeTuesZN = 0, timeWZN = 0, timeThurZN = 0, timeFZN = 0, timeSZN = 0; //время ЧС и ЗН недель
    public static boolean chisloW, chisloG; //проверка, что доп. время введено
    boolean nowChooseWeek = false; //проверка, что будильники удалены
    boolean vibor = false;
    int ArrayDays[] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);//передача строчки экшена
    SharedPreferences sPref;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgtu_baumanka);

        sw1 = (Switch) findViewById(R.id.switch1);
        sw2 = (Switch) findViewById(R.id.switch2);

        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                if (checked) {
                    if (vibor == false) {
                        nowWeek = 0; //числитель
                        vibor = true;
                    } else {
                        alreadyChoose();
                        sw1.getTextOff();
                    }
                } else {
                    nowWeek = 2;
                    weekNorChoose();
                    vibor = false;
                }
            }
        });

        sw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                if (checked) {
                    if (vibor == false) {
                        nowWeek = 1; //заменатель
                        vibor = true;
                    } else {
                        alreadyChoose();
                        sw2.getTextOff();
                    }
                } else {
                    nowWeek = 2;
                    weekNorChoose();
                    vibor = false;
                }
            }
        });
    }

    public void alreadyChoose() {
        Toast.makeText(this, "неделя выбрана", Toast.LENGTH_LONG).show();
    }

    public void weekNorChoose() {
        Toast.makeText(this, "неделя не выбрана", Toast.LENGTH_LONG).show();
    }

    public void learnTime(View v) {
        timeMondayMgtu = (EditText) findViewById(R.id.timeInMondayMgtu);
        timeTuesdayMgtu = (EditText) findViewById(R.id.timeInTuesdayMgtu);
        timeWednesdayMgtu = (EditText) findViewById(R.id.timeInWednesdayMgtu);
        timeThursdayMgtu = (EditText) findViewById(R.id.timeInThursdayMgtu);
        timeFridayMgtu = (EditText) findViewById(R.id.timeInFridayMgtu);
        timeSaturdayMgtu = (EditText) findViewById(R.id.timeInSaturdayMgtu);

        longTextMMgtu = timeMondayMgtu.getText().toString().length();
        longTextTuesMgtu = timeTuesdayMgtu.getText().toString().length();
        longTextWenMgtu = timeWednesdayMgtu.getText().toString().length();
        longTextThurMgtu = timeThursdayMgtu.getText().toString().length();
        longTextFMgtu = timeFridayMgtu.getText().toString().length();
        longTextSMgtu = timeSaturdayMgtu.getText().toString().length();

        if (nowWeek != 2) {
            if (longTextMMgtu != 0) {
                try {
                    if (Integer.parseInt(timeMondayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeMondayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeMondayMgtu.getText().toString()) % 100 < 60)
                        {
                            if (nowWeek == 0) {
                                timeMCH = Integer.parseInt(timeMondayMgtu.getText().toString());
                                Log.i("COCECA01", timeMCH +"");
                                ArrayDays[8] = timeMCH;
                            } else if (nowWeek == 1)
                            {
                                timeMZN = Integer.parseInt(timeMondayMgtu.getText().toString());
                                Log.i("COCECA02", timeMZN +"");
                                ArrayDays[9] = timeMZN;
                            }
                        }
                        else {
                            attention(0, -470, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, -470, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, -470, "Введите число");
                }
            }
            if (longTextTuesMgtu != 0) {
                try {
                    if (Integer.parseInt(timeTuesdayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeTuesdayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeTuesdayMgtu.getText().toString()) % 100 < 60) {
                            if (nowWeek == 0) {
                                timeTuesCH = Integer.parseInt(timeTuesdayMgtu.getText().toString());
                                Log.i("COCECA03", timeTuesCH +"");
                                ArrayDays[10] = timeTuesCH;
                            } else if (nowWeek == 1) {
                                timeTuesZN = Integer.parseInt(timeTuesdayMgtu.getText().toString());
                                Log.i("COCECA04", timeTuesZN +"");
                                ArrayDays[11] = timeTuesZN;
                            }
                        } else {
                            attention(0, -360, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, -360, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, -360, "Введите число");
                }
            }
            if (longTextWenMgtu != 0) {
                try {
                    if (Integer.parseInt(timeWednesdayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeWednesdayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeWednesdayMgtu.getText().toString()) % 100 < 60) {
                            if (nowWeek == 0) {
                                timeWCH = Integer.parseInt(timeWednesdayMgtu.getText().toString());
                                ArrayDays[12] = timeWCH;
                            } else if (nowWeek == 1) {
                                timeWZN = Integer.parseInt(timeWednesdayMgtu.getText().toString());
                                ArrayDays[13] = timeWZN;
                            }
                        } else {
                            attention(0, -250, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, -250, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, -250, "Введите число");
                }
            }
            if (longTextThurMgtu != 0) {
                try {
                    if (Integer.parseInt(timeThursdayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeThursdayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeThursdayMgtu.getText().toString()) % 100 < 60) {
                            if (nowWeek == 0) {
                                timeThurCH = Integer.parseInt(timeThursdayMgtu.getText().toString());
                                ArrayDays[14] = timeThurCH;
                            } else if (nowWeek == 1) {
                                timeThurZN = Integer.parseInt(timeThursdayMgtu.getText().toString());
                                ArrayDays[15] = timeThurZN;
                            }
                        } else {
                            attention(0, -140, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, -140, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, -140, "Введите число");
                }
            }
            if (longTextFMgtu != 0) {
                try {
                    if (Integer.parseInt(timeFridayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeFridayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeFridayMgtu.getText().toString()) % 100 < 60) {
                            if (nowWeek == 0) {
                                timeFCH = Integer.parseInt(timeFridayMgtu.getText().toString());
                                ArrayDays[16] = timeFCH;
                            } else if (nowWeek == 1) {
                                timeFZN = Integer.parseInt(timeFridayMgtu.getText().toString());
                                ArrayDays[17] = timeFZN;
                            }
                        } else {
                            attention(0, -34, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, -34, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, -34, "Введите число");
                }
            }
            if (longTextSMgtu != 0) {
                try {
                    if (Integer.parseInt(timeSaturdayMgtu.getText().toString()) / 100 >= 8) {
                        if (Integer.parseInt(timeSaturdayMgtu.getText().toString()) / 100 < 24 && Integer.parseInt(timeSaturdayMgtu.getText().toString()) % 100 < 60) {
                            if (nowWeek == 0) {
                                timeSCH = Integer.parseInt(timeSaturdayMgtu.getText().toString());
                                ArrayDays[18] = timeSCH;
                            } else if (nowWeek == 1) {
                                timeSZN = Integer.parseInt(timeSaturdayMgtu.getText().toString());
                                ArrayDays[19] = timeSZN;
                            }
                        } else {
                            attention(0, 80, "Число не соответствует временным размерностям");
                        }
                    } else {
                        attention(0, 80, "Введите время не меньше 8 часов");
                    }
                } catch (NumberFormatException e) {
                    attention(0, 80, "Введите число");
                }
            }
        } else {
            Toast.makeText(this, "не выбрано ни ЧС, ни ЗН", Toast.LENGTH_LONG).show();
        }
    }

    public void ChooseWeek() {
        //открытие данных и выгрузка всех ланных, тут время нужно обеих недель и доп времени
        sPref = getPreferences(MODE_PRIVATE);
        String loadText = sPref.getString("info", "");
        timeWakeUpH = Character.getNumericValue(loadText.charAt(2));
        timeWakeUpM = Character.getNumericValue(loadText.charAt(3));
        timeGoH = Character.getNumericValue(loadText.charAt(4));
        timeGoM = Character.getNumericValue(loadText.charAt(5));
        howManyWake = Character.getNumericValue(loadText.charAt(0));
        howManyOut = Character.getNumericValue(loadText.charAt(1));
        timeIntervalW = Character.getNumericValue(loadText.charAt(6));
        timeIntervalG = Character.getNumericValue(loadText.charAt(7));
        timeMCH = Character.getNumericValue(loadText.charAt(8));
        timeTuesCH = Character.getNumericValue(loadText.charAt(10));
        timeWCH = Character.getNumericValue(loadText.charAt(12));
        timeThurCH = Character.getNumericValue(loadText.charAt(14));
        timeFCH = Character.getNumericValue(loadText.charAt(16));
        timeSCH = Character.getNumericValue(loadText.charAt(18));
        timeMZN = Character.getNumericValue(loadText.charAt(9));
        timeTuesZN = Character.getNumericValue(loadText.charAt(11));
        timeWZN = Character.getNumericValue(loadText.charAt(13));
        timeThurZN = Character.getNumericValue(loadText.charAt(15));
        timeFZN = Character.getNumericValue(loadText.charAt(17));
        timeSZN = Character.getNumericValue(loadText.charAt(19));

        DeleteToChange();
        TimeOnWeek();
        CreateParametersForAlarm();
    }

    public void DeleteToChange() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.ACTION_DISMISS_ALARM,"");
        startActivity(intent);
    }

    public void TimeOnWeek() {
        if (nowWeek == 0) //числитель
        {
            timeMMgtu = timeMCH;
            timeTuMgtu = timeTuesCH;
            timeWMgtu = timeWCH;
            timeThMgtu = timeThurCH;
            timeFMgtu = timeFCH;
            timeSMgtu = timeSCH;
        }
        else if (nowWeek == 1) //знаменатель
        {
            timeMMgtu = timeMZN;
            timeTuMgtu = timeTuesZN;
            timeWMgtu = timeWZN;
            timeThMgtu = timeThurZN;
            timeFMgtu = timeFZN;
            timeSMgtu = timeSZN;
        }
    }

    public void CreateTimeOnClick(View v) {
        //сохранение текущей недели в файл

        if ((chisloW == true) && (chisloG == true)) //если введено доп время
        {
            if (nowChooseWeek == false) {
                if (nowWeek != 2) {
                    TimeOnWeek();
                    CreateParametersForAlarm();
                    nowChooseWeek = true;

                    ArrayDays[20] = nowWeek;
                    saveInfo();
                } else {
                    attention(0, 350, "Выберите неделю, на которую будете создавать будильники");
                }
            } else {
                attention(0, 350, "Удалите будильники выставленной недели ЧС/ЗН");
            }
        } else {
            attention(0, 350, "Не указано доп. время (на подъем и на сборы)");
        }
    }

    public void CreateParametersForAlarm() {
        if ((timeMMgtu != 0) || (timeTuMgtu != 0) || (timeWMgtu != 0) || (timeThMgtu != 0) || (timeFMgtu != 0) || (timeSMgtu != 0)) {
            if (timeMMgtu != 0) {
                ArrayList<Integer> alarmDaysM = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.MONDAY,
                        timeMMgtu, -140, -480, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysM);
            }

            if (timeTuMgtu != 0) {
                ArrayList<Integer> alarmDaysTu = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.TUESDAY,
                        timeTuMgtu, -140, -380, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysTu);
            }

            if (timeWMgtu != 0) {
                ArrayList<Integer> alarmDaysW = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.WEDNESDAY,
                        timeWMgtu, -140, -270, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysW);
            }

            if (timeThMgtu != 0) {
                ArrayList<Integer> alarmDaysTh = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.TUESDAY,
                        timeThMgtu, -140, -160, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysTh);
            }

            if (timeFMgtu != 0) {
                ArrayList<Integer> alarmDaysF = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.FRIDAY,
                        timeFMgtu, -140, -34, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysF);
            }

            if (timeSMgtu != 0) {
                ArrayList<Integer> alarmDaysS = new ArrayList<>();
                AlarmCreate(timeGoH, timeGoM, timeWakeUpH, timeWakeUpM, Calendar.SATURDAY,
                        timeSMgtu, -140, 80, howManyWake, howManyOut, timeIntervalW, timeIntervalG, alarmDaysS);
            }
        } else {
            attention(0, 360, "Не введено время ни одной пары");
        }
    }

    public void AlarmCreate(int hTimeG, int mTimeG, int hTimeWake, int mTimeWake, int dayOfWeek, int time, int x, int y, int howWake, int howOut, int intervalW, int intervalG, ArrayList massivAlarm) {

        int hTime = time / 100;
        int mTime = time % 100;

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
            intent.putExtra(AlarmClock.EXTRA_HOUR, hTime);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, mTime);
            massivAlarm.add(dayOfWeek);
            intent.putExtra(AlarmClock.EXTRA_DAYS, massivAlarm);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Выход");
            if (context != null) {
                context.startActivity(intent);
            } else {
                startActivity(intent);
            }

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
            intent.putExtra(AlarmClock.EXTRA_HOUR, hTime);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, mTime);
            massivAlarm.add(dayOfWeek);
            intent.putExtra(AlarmClock.EXTRA_DAYS, massivAlarm);
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Вставать");
            if (context != null) {
                context.startActivity(intent);
            } else {
                startActivity(intent);
            }

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

    public void saveInfo() {
        //сохранение данных доп времени
        ArrayDays[0] = howManyWake;
        ArrayDays[1] = howManyOut;
        ArrayDays[2] = timeWakeUpH;
        ArrayDays[3] = timeWakeUpM;
        ArrayDays[4] = timeGoH;
        ArrayDays[5] = timeGoM;
        ArrayDays[6] = timeIntervalW;
        ArrayDays[7] = timeIntervalG;

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        String ArrayS = ArrayDays.toString();
        ed.putString("info", ArrayS);
        ed.apply();
        Toast.makeText(MgtuBaumanka.this, ArrayS, Toast.LENGTH_SHORT).show();
    }

    public void attention(int x, int y, String s) {
        Toast toast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    public void onButtonDeleteAlarm(View v) {
        nowChooseWeek = false;
    }

    public void onButtonStandardPlanTimeWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MgtuBaumanka.this, TimeFirstLesson.class);
        startActivity(intent);
    }

    public void onButtonClickSettingOtherTimeWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MgtuBaumanka.this, SettingOtherTime.class);
        startActivity(intent);
    }

    public void onButtonDeleteAlarmClock(View v) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.ACTION_DISMISS_ALARM,"");
        startActivity(intent);
    }

    public void onButtonClickMainActivityWindow(View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MgtuBaumanka.this, MainActivity.class);
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        saveInfo();
    }
}