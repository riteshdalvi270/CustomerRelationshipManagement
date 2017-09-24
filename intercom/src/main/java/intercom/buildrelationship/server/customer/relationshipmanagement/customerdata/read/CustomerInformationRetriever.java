package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read;

import intercom.buildrelationship.object.response.CustomerResponse;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.CustomersToInvite;
import org.codehaus.jettison.json.JSONException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class to determine the customer data to read and read the data and return List of {@link CustomerResponse}. Different implementation of this class will provide different ways to read the customer file.
 * @author Ritesh Dalvi.
 */
public abstract class CustomerInformationRetriever {

    private final Logger logger = Logger.getLogger(getClass().getName());

    protected String fileToRead;
    protected CustomersToInvite customersToInvite;

    /**
     * Set the file to read.
     * @param fileToRead The file to read from.
     * @throws {@link intercom.buildrelationship.exception.VerifyException} when input provided is null, empty or blank.
     */
    public abstract void fileToRead(final String fileToRead);

    /**
     * Read the customer data.
     * @return non-null, possibly empty List of {@link CustomerResponse}.
     */
    public List<CustomerResponse> readCustomerData() {

        final List<CustomerResponse> customerResponses = new ArrayList<>();

        try {
            final FileReader fileReader = new FileReader(fileToRead);

            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readData = null;

            while((readData = bufferedReader.readLine())!=null) {

                final CustomerResponse customerResponse = customersToInvite.invite(readData);

                if(customerResponse != null) {
                    customerResponses.add(customerResponse);
                }
            }

            Collections.sort(customerResponses, new Comparator<CustomerResponse>() {
                @Override
                public int compare(CustomerResponse customerResponse1, CustomerResponse customerResponse2) {
                    return Integer.valueOf(customerResponse1.getCustomerUserId()).compareTo(Integer.valueOf(customerResponse2.getCustomerUserId()));
                }
            });

        }catch (final FileNotFoundException fileNotFound) {

            logger.log(Level.SEVERE,"Customer data file not found",fileNotFound);

        }catch (final IOException ioException) {

            logger.log(Level.SEVERE,"Failed while reading customer data",ioException);

        } catch (JSONException jsonException) {

            logger.log(Level.SEVERE,"Failed while reading customer data",jsonException);

        }

        return customerResponses;
    }
}
