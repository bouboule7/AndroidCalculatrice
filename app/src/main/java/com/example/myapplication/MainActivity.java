package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class MainActivity extends AppCompatActivity {

    String calcul ="0",affichage="0",retour="0";
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        HorizontalScrollView scroll;
        Button[] bt = new Button[10];
        Button plus,moins, fois, diviser;
        Button egale, virgule,clear, delete,left, right;
        TextView entrer, reponse;
        bt[0] = (Button) findViewById(R.id.bt0);
        bt[1] = (Button) findViewById(R.id.bt1);
        bt[2] = (Button) findViewById(R.id.bt2);
        bt[3] = (Button) findViewById(R.id.bt3);
        bt[4] = (Button) findViewById(R.id.bt4);
        bt[5] = (Button) findViewById(R.id.bt5);
        bt[6] = (Button) findViewById(R.id.bt6);
        bt[7] = (Button) findViewById(R.id.bt7);
        bt[8] = (Button) findViewById(R.id.bt8);
        bt[9] = (Button) findViewById(R.id.bt9);
        plus=(Button) findViewById(R.id.plus);
        moins=(Button)findViewById(R.id.moins);
        fois=( Button)findViewById(R.id.fois);
        diviser=(Button)findViewById(R.id.division);
        egale=(Button)  findViewById(R.id.egale);
        virgule=(Button)findViewById(R.id.virgule);
        clear=(Button)findViewById(R.id.clear);
        delete=(Button)findViewById(R.id.delete);
        left=(Button)findViewById(R.id.left);
        right=(Button)findViewById(R.id.right);
        reponse=(TextView)findViewById(R.id.reponse);
        entrer=(TextView)findViewById(R.id.calcule);
        scroll= (HorizontalScrollView) findViewById(R.id.scroll);

        for(i=0;i<10;i++) {
            int valeur=i;
            bt[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg) {
                    retour=affichage;
                    if(affichage.charAt(affichage.length()-1) == ')'){
                        if(calcul.equals("0")) {
                            calcul = "";
                            affichage="";
                        }
                        calcul +='*'+String.valueOf(valeur);
                    }
                    else {
                        if (calcul.equals("0")) {
                            calcul = "";
                            affichage = "";
                        }
                        calcul += String.valueOf(valeur);
                    }
                    affichage+=String.valueOf(valeur);
                    changement(calcul,affichage, entrer,reponse, scroll);
                }
            });
        }
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                if(calcul.equals("0")) {
                    calcul = "";
                    affichage = "";
                }
                calcul +="+";
                affichage+="+";
                changement(calcul,affichage,entrer,reponse,scroll);
            }
        });
        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                if(calcul.equals("0")) {
                    calcul = "";
                    affichage = "";
                }
                calcul +="-";
                affichage+="-";
                changement(calcul,affichage,entrer,reponse,scroll);
            }
        });
        fois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                calcul +="*";
                affichage+="*";
                changement(calcul,affichage, entrer,reponse, scroll);
            }
        });
        diviser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                calcul +="/";
                affichage+="/";
                changement(calcul,affichage, entrer,reponse, scroll);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                if(calcul.equals("0")) {
                    calcul = "";
                    affichage="";
                    calcul +="(";
                }
                else if((affichage.charAt(affichage.length()-1)=='/')||
                        (affichage.charAt(affichage.length()-1)=='*') ||
                        (affichage.charAt(affichage.length()-1)=='+') ||
                        (affichage.charAt(affichage.length()-1)=='-')) {
                    calcul += "(";
                }
                else
                    calcul += "*(";
                affichage += "(";
                changement(calcul,affichage, entrer, reponse,scroll);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                if(calcul.equals("0")) {
                    calcul = "";
                    affichage="";
                }
                calcul +=")";
                affichage+=")";
                changement(calcul,affichage, entrer,reponse, scroll);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                calcul ="0";
                affichage="0";
                changement(calcul,affichage, entrer, reponse,scroll);
            }
        });
        virgule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;
                calcul +=".";
                affichage+=".";
                changement(calcul,affichage, entrer,reponse, scroll);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                retour=affichage;

                if(affichage.length()==1 && !affichage.equals("0")) {
                    calcul = "0";
                    affichage = "0";
                }
                if(calcul.length()>1) {
                    if ((affichage.charAt(affichage.length() - 1) == '(') || (affichage.charAt(affichage.length() - 1) == ')')){
                        calcul = calcul.substring(0, calcul.length() - 2);
                        affichage = affichage.substring(0, affichage.length() - 1);
                    }
                    else{
                        calcul = calcul.substring(0, calcul.length() - 1);
                        affichage = affichage.substring(0, affichage.length() - 1);
                    }
                }
                changement(calcul,affichage, entrer,reponse, scroll);
            }
        });

        egale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");

                try{
                    Double result = (double)engine.eval(calcul);

                    entrer.setText(result.toString());
                    reponse.setText("0.0");
                    calcul=result.toString();
                }catch (Exception e){
                    entrer.setText("Invalide");
                    reponse.setText("0.0");
                }
            }
        });
    }

    public void changement(String calcul,String affichage, TextView entrer, TextView sortie, HorizontalScrollView scroll) {
        entrer.setText(affichage);
        if(affichage.length()>10 && affichage.length()<15)
            entrer.setTextSize(40);
        else if(affichage.length()>14)
            entrer.setTextSize(30);
        else
            entrer.setTextSize(50);
        if(affichage.length()>18){
            if(affichage.length()>retour.length())
                doScrollRight(scroll);
            if(affichage.length()<retour.length())
                doScrollLeft(scroll);
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        try{
            Double result = (double)engine.eval(calcul);

            sortie.setText(result.toString());
        }catch (Exception e){
            sortie.setText("Expression invalide");
        }
    }

    private void doScrollLeft(HorizontalScrollView scroll) {
        
        int x = scroll.getScrollX();
        int y = scroll.getScrollY();

        if(x - 35 >= 0) {
            scroll.scrollTo(x-35, y);
        }

    }

    private void doScrollRight(HorizontalScrollView scroll) {
        int maxAmount = scroll.getMaxScrollAmount();

        int x = scroll.getScrollX();
        int y = scroll.getScrollY();

        if(x + 35 <= maxAmount*4) {
            scroll.scrollTo(x+35, y );
        }
    }
}