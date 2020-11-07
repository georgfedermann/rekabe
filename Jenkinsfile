//noinspection GroovyAssignabilityCheck
node {
    stage('Build') {
        sh label: 'echo date', script: 'echo "Current timestamp is $(date)"'
        echo "Branch name is ${env.BRANCH_NAME}"
        echo "Global variable params is ${params}"
        echo "Global variable currentBuild id ${currentBuild}"

        sh label: 'Build docker image', script: '''docker image build --tag "${dockerhub_u}/rekabe" . && env
docker login -u "${dockerhub_u}" -p "${dockerhub_p}" registry-1.docker.io
docker push "${dockerhub_u}/rekabe:latest"
'''
    }

    stage('Deploy to QA') {
        echo "Deploying to stage QA"
    }

    stage('Test') {
        echo "Run component tests"
    }

    stage('Deploy to PROD') {
        echo "Deploying to stage PROD"
    }
}