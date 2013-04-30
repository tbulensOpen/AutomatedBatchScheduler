package org.tbulens.abs.domain.repository

import org.tbulens.abs.domain.model.BatchJob


public interface AbsRepository {

    List<BatchJob> findAllBatchJobs()

    BatchJob findBatchJobByName(String batchJob)

}