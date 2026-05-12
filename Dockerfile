FROM ubuntu:latest
LABEL authors="mara"

ENTRYPOINT ["top", "-b"]