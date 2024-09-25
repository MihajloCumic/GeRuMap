package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.repository.composite.MapNode;

import java.awt.*;

public class Veza extends Element{

    private Pojam pojamOd;
    private Pojam pojamDo;

    public Veza(String name, MapNode parent) {
        super(name, parent);
        this.type = "Veza";
    }

    public Pojam getPojamOd() {
        return pojamOd;
    }

    public void setPojamOd(Pojam pojamOd) {
        this.pojamOd = pojamOd;
    }

    public Pojam getPojamDo() {
        return pojamDo;
    }

    public void setPojamDo(Pojam pojamDo) {
        this.pojamDo = pojamDo;
    }
}
