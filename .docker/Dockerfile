FROM dockerproxy.repos.tech.orange/bitnami/java:11

COPY sonar-cacerts /opt/bitnami/java/lib/security/cacerts
COPY certificateChain.pem //opt/bitnami/java/lib/security/certificateChain.pem
COPY certificateChain.pem /usr/local/share/ca-certificates/certificateChain.pem
RUN update-ca-certificates