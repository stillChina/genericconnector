/*
   Copyright 2015 Ant Kutschera

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package ch.maxant.jca_demo.bookingsystem;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="BookingSystem")
public class BookingSystemWebService {

	public static final AtomicInteger CALLS_EXECUTE = new AtomicInteger();
	public static final AtomicInteger CALLS_COMMIT = new AtomicInteger();
	public static final AtomicInteger CALLS_ROLLBACK = new AtomicInteger();
	
    public BookingSystemWebService(){
    }
    
    private final Logger log = Logger.getLogger(this.getClass().getName());
    
    public String reserveTickets(@WebParam(name="txid") String txid, @WebParam(name="referenceNumber") String referenceNumber) throws Exception {
    	CALLS_EXECUTE.incrementAndGet();
        log.log(Level.INFO, "EXECUTE: booking tickets with reference number " + referenceNumber + " for TXID " + txid);
        if("FAILWSBookingSystem".equals(referenceNumber)){
            throw new Exception("failed for test purposes");
        }else{
            //TODO write persistently!
            return "RESERVED tickets: " + referenceNumber;
        }
    }

    public void bookTickets(@WebParam(name="txId") String txId) throws IOException{
    	CALLS_COMMIT.incrementAndGet();
        
        //TODO write persistently!
        
        log.log(Level.INFO, "BOOK tickets: " + txId);
    }

    public void cancelTickets(@WebParam(name="txId") String txId) throws IOException {
    	CALLS_ROLLBACK.incrementAndGet();
        
        //TODO write persistently!

        log.log(Level.INFO, "CANCEL tickets: " + txId);
    }
    
}
