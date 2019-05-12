package com.example.employee.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Hari Choudhary on 1/16/2019 at 10:49 AM .
 * Core techies
 * hari@coretechies.com
 */

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
