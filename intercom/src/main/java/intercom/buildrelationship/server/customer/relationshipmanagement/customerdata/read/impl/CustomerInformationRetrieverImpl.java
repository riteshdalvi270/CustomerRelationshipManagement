package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.impl;

import intercom.buildrelationship.exception.Verifier;
import intercom.buildrelationship.object.criteria.offices.Offices;
import intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.read.CustomerInformationRetriever;
import intercom.buildrelationship.server.customer.relationshipmanagement.invite.impl.CustomersToInviteOnGeoLocation;

/**
 * Read customer information. {@link CustomerInformationRetrieverImpl} only manages reading of customer data and does not perform any kind of business logic whatsoever.
 * @author Ritesh Dalvi.
 */
public class CustomerInformationRetrieverImpl extends CustomerInformationRetriever {

    /**
     * Creates instance of {@link CustomerInformationRetrieverImpl}.
     * @param office Represents the office for which we need to invite the customers (cannot be null).
     * @return non-null {@link CustomerInformationRetriever}.
     */
    public static CustomerInformationRetriever createInstance(final Offices office) {
        return new CustomerInformationRetrieverImpl(office);
    }

    /**
     * Constructor. Private to avoid direct instantiation.
     * @throws {@link intercom.buildrelationship.exception.VerifyException} if input is null.
     */
    private CustomerInformationRetrieverImpl(final Offices office) {
        Verifier.verifyNotNull(office, "Input office:null");
        customersToInvite = CustomersToInviteOnGeoLocation.customersToInvite(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fileToRead(String fileToRead) {
        Verifier.verifyBlank(fileToRead,"File provided is invalid: null,empty or blank");
        this.fileToRead = fileToRead;
    }
}
