package broker;

import org.springframework.cloud.servicebroker.model.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

@Service
public class ServiceInstances implements ServiceInstanceService {
    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest createServiceInstanceRequest) {
        return null;
    }

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest getLastServiceOperationRequest) {
        return null;
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest deleteServiceInstanceRequest) {
        return null;
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest updateServiceInstanceRequest) {
        return null;
    }
}
