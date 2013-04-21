package org.tbulens.abs.batchscheduler.service

import org.gmock.WithGMock
import org.junit.Before
import org.junit.Test
import org.tbulens.abs.domain.repository.AbsRepository
import sun.reflect.generics.repository.AbstractRepository

@WithGMock
class BatchJobScheduleServiceTest {

    BatchJobScheduleService batchJobScheduleService
    AbsRepository mockAbsRepository

    @Before
    void setUp() {
        mockAbsRepository = mock(AbsRepository)
        batchJobScheduleService = new BatchJobScheduleService(absRepository: mockAbsRepository)
    }

    @Test
    void load() {

    }
}
