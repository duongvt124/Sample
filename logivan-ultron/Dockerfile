FROM minhnhut86/maven_logivan_qa_team:base
COPY . .
ENV TEST_STATUS=0
RUN mvn test -P EnvironmentTestbed -D it.test=lgv.automation.serenityCucumber.api.features.Tester -D skip.test=true clean verify
