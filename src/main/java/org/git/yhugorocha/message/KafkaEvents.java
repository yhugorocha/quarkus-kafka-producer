package org.git.yhugorocha.message;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.git.yhugorocha.dto.AuditDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Channel("audit-out")
    Emitter<AuditDTO> auditDTOEmitter;

    public void sendNewKafkaEvent(AuditDTO auditDTO){
        logger.info("-- Enviando Objeto para Topico kafka --");
        auditDTOEmitter.send(auditDTO).toCompletableFuture().join();
    }
}
