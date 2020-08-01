package ru.progwards.java2.lessons.graph;

import java.util.List;

public class FindUnused {

    static class CObject {
        public List<CObject> references;
        int mark;

        CObject(List<CObject> references, int mark) {
            this.references = references;
            this.mark = mark;
        }
    }

//    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
//
//    }
}
