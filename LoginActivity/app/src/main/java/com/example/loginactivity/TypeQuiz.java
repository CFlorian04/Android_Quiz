package com.example.loginactivity;

import java.text.Normalizer;

public class TypeQuiz {
    private String name, srcIMG;
    private int idType;

    public TypeQuiz(String name, int idType) {
        this.name = name;
        this.idType = idType;
        this.srcIMG = setsrcIMG(name);
        System.out.println(getSrcIMG());
    }

    public String getName() {
        return name;
    }

    public String getSrcIMG() {
        return srcIMG;
    }

    public int getIdType() {
        return idType;
    }

    private String setsrcIMG(String name) {
        name = name.toLowerCase();
        name = name.replace("-","_").replace(" ","_").replace("é","e");
        name = Normalizer.normalize(name, Normalizer.Form.NFD);

        return "quiz_" + name;
    }
}
