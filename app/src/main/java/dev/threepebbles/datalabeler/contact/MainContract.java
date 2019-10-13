package dev.threepebbles.datalabeler.contact;

public interface MainContract {

    interface View {
        void updateTextView(String data);

        void clearTextView();
    }

    interface Presenter {
        void updateTextView(String data);

        void clearTextView();
    }

}
