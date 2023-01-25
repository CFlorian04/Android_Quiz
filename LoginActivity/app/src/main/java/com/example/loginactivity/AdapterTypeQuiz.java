package com.example.loginactivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class AdapterTypeQuiz extends ArrayAdapter {
    Activity activity;
    ArrayList<TypeQuiz> items;
    int customViewResourceId;

    public AdapterTypeQuiz(Activity activity, int customViewResourceId, ArrayList<TypeQuiz> items) {
        super(activity, customViewResourceId,items);
        this.customViewResourceId = customViewResourceId;
        this.items = items;
        this.activity=activity;
        System.out.println("ADAPTER");
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View layout = convertView;

        if (convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            layout = inflater.inflate(customViewResourceId,parent, false);
        }
        TextView nameTV = (TextView) layout.findViewById(R.id.districtTV);
        nameTV.setText(items.get(position).getName());

        ImageView flagIV = (ImageView) layout.findViewById(R.id.districtIMG);
        flagIV.setImageResource(activity.getResources().getIdentifier(items.get(position).getSrcIMG(), "drawable", activity.getPackageName()));  //.setImageResource(getResources().getIdentifier(items.get(position).getImg(), "drawable", getPackageName()));

        dialogClick(layout, items.get(position).getIdType(),items.get(position).getName());

        View rectangle = (View) layout.findViewById(R.id.myRectangleView);
        rectangle.requestLayout();
        rectangle.getLayoutParams().width = 35 * items.get(position).getName().length();

        return layout;
    }

    void dialogClick(View layout, int typeQuiz, String nameQuiz){
        ImageView img = (ImageView) layout.findViewById(R.id.districtIMG);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Commencer le quiz ?");
                builder.setMessage("Quiz sur "+nameQuiz);
                builder.setPositiveButton("OUI"
                        , new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                Intent intent = new Intent(activity, QuizActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("userInfo", MenuActivity.getUser());
                                bundle.putSerializable("typeQuiz", typeQuiz);
                                intent.putExtras(bundle);
                                activity.startActivity(intent);

                            }
                        });
                builder.setNegativeButton("NON", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) { dialog.dismiss(); }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

}
