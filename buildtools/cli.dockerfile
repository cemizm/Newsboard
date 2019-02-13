FROM openjdk:8

RUN apt-get -y update ; apt-get -y install curl && \
	curl -sL https://deb.nodesource.com/setup_9.x | bash  && \
	apt-get -y install nodejs && \
	npm -g install bower

RUN groupadd --system --gid 1000 newsboard && \ 
	useradd --system --gid newsboard --uid 1000 --shell /bin/bash --create-home newsboard && \
	chown --recursive newsboard:newsboard /home/newsboard

USER newsboard
VOLUME "/home/newsboard/source"
WORKDIR /home/newsboard/source

