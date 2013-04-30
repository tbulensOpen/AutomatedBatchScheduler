package org.tbulens.abs.domain.model

//class JobStatus {
//     static int WAITING = 0
//     static int CREATED = 1
//     static int REQUESTED = 2
//     static int EXECUTING = 3
//     static int COMPLETED = 4
//     static int FAILED = 5
//     static int STOP = 6
//     static int DISABLED = 7

enum JobStatus {
    WAITING(0), CREATED(1), REQUESTED(2), EXECUTING(3), COMPLETED(4), FAILED(5), STOP(6), DISABLED(7)

    JobStatus(int value) { this.value = value }

    private final int value

    public int value() { return value }
}


