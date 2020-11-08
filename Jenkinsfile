// noinspection GroovyAssignabilityCheck
node {
    stage('Build') {
        sh label: 'echo date', script: 'echo "Current timestamp is $(date)"'

        checkout([$class: 'GitSCM', branches: [[name: '*/master']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions: [],
                  submoduleCfg: [],
                  userRemoteConfigs: [[name: 'github', refspec: '+refs/heads/master:refs/remotes/github/master',
                                       url: 'https://github.com/georgfedermann/rekabe.git']]])

        echo "Job name is ${env.JOB_NAME}"
        echo "Node name is ${env.NODE_NAME}"
        echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        echo "Global variable params is ${params}"
        echo "Global variable currentBuild id ${currentBuild}"

        withCredentials([usernamePassword(credentialsId:'docker_hub', passwordVariable: dockerhub_p, usernameVariable: 'dockerhub_u')]) {
            sh label: 'Build docker image',
                    script: ''' docker image build --tag "${dockerhub_u}/rekabe" . || exit 1
                            docker login -u "${dockerhub_u}" -p "${dockerhub_p}" registry-1.docker.io
                            docker push "${dockerhub_u}/rekabe:latest"
'''
        }
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