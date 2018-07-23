package com.gmail.kamil.jarmusik.DataRepoGithub.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

class DateFormatFactory {
    static DateFormat iso() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }
    static DateFormat isoSameDate() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
