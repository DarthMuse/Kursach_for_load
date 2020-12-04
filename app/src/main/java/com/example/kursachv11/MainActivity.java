package com.example.kursachv11;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickClose(View v) //закрытие приложение по нажатию кнопки
    {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    public void onButtonClickTimeFirstLessonWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MainActivity.this, TimeFirstLesson.class);
        startActivity(intent);
    }

    public void onButtonClickMGTUWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MainActivity.this, MgtuBaumanka.class);
        startActivity(intent);
    }

    public void onButtonClickSettingWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MainActivity.this, SettingOtherTime.class);
        startActivity(intent);
    }

    public void onButtonClickInstructionWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MainActivity.this, Instruction.class);
        startActivity(intent);
    }

    public void onButtonClickChangeWeekWindow (View v) //переход на сраницу с инструкций по нажатии кнопки
    {
        Intent intent = new Intent(MainActivity.this, ChangeWeek.class);
        startActivity(intent);
    }
}