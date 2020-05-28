package ru.coriolis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Numbers {

    private long created = Calendar.getInstance().getTimeInMillis();

    private int val1;
    private int val2;
}
