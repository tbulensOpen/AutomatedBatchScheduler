AutomatedBatchScheduler
=======================

Automated Batch Scheduler (ABS) - JVM based, Decouple Triggering of Jobs from execution of jobs.

Purpose:  
   Provide a batch job scheduler that decouples the requesting of batch jobs, execution of batch jobs, administration of batch jobs, Event Hanlder, 
   and Batch Job Logging Handler.
   
Overview:
  ABS Batch Scheduler:
     Reads in a configuration ( job name, trigger expression, data map ) and dynamically builds a list of
     triggers to detech which jobs should be executed. Provide some sort of grouping to allow a single job
     per group to be executed in order, but, allow as many groups to be executed concurrently. 

     Batch scheduler will initiate a http request with a configurable url, passing in the job name, data map, and a unique job number.
     
  Executing of batch jobs:
     ESB will recieve the http request, determine the message type, transform the data map from request parameters to a 
     map, and call the Java ( any JVM compatible language ) component passing in the data map. 
     
     The ESB will also send a event message to ABS Event Handler, with the job name and data map, indicating job as been
     requested.
     
     Batch Job Responsiblies:
        Once the message has been received and data map arguments validated, 
             an HTTP request is made back to the ESB to indicating job is now executing.
             
        Also, the batch job can send occassional updates indicating the job is still processing.
        
     ESB: If no exception message is thrown from batch job, a success event message is sent to ABS Event Handler.
          If exception is thrown by the batch job, a failure event message is sent to ABS Event Handler, 
          and exception message is sent to the Batch Job Logging Handler.
          
  
     ESB: recieves an ABS event message, calls the ABS Event Handler. 
     
   ABS Event Handler:
      Recieves incoming event messages and updates the ABS data store with current status.
      
   ABS Administration:
      Web Application that is the client to start/stop jobs, request rerun of jobs, and veiw status of jobs. 
      Stopping a job doesn't actually stop the trigger, but, marks the job as not runnable.
      It is the responsibity of Batch Job Scheduler triggers to determine if a request should be made based on Job Status.
                
   ABS Logging Handler:
      Handles all exceptions of Jobs.
      
   Batch Job:
      Resposible to make a Http Request that it started executing.
      Optional to make Http requet that is continually executing.
     

Why this Architecture:
    Job Scheduler responsible for only makeing a request to run a job with its parameters.
    Batch job is to run the job, and make a request that it is executing.
    Administration can monitor, view, start and stop jobs.
    Event Handler is responsible to handing events that occur. Such as requested, executing, success, and failed.
    ESB allows the decoupling of responsibilities. 
    
    Why http request:
       Http does not have dependencies, its flexible, and you can put load balancers in front of it.
                            