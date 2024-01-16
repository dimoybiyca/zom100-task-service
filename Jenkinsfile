pipeline {
    agent any

    stages{
        stage('Cleanup') {
            steps {
                deleteDir()
            }
        }
        stage('Pull') {
            steps{
                git branch: 'dev',
                    url: 'https://github.com/dimoybiyca/zom100-task-service.git'
            }
        }
        stage('Build') {
            steps{
                sh "docker build -t 192.168.0.51:5000/zm-task-service:0.${env.BUILD_NUMBER} ."
                sh "docker tag 192.168.0.51:5000/zm-task-service:0.${env.BUILD_NUMBER} 192.168.0.51:5000/zm-task-service:latest"
            }
        }
        stage('Publish') {
            steps{
                sh "docker push 192.168.0.51:5000/zm-task-service:0.${env.BUILD_NUMBER}"
                sh "docker push 192.168.0.51:5000/zm-task-service:latest"
            }
        }
        // stage('Deploy') {
        //     steps{
        //         dir('ci/Ansible') {
        //             sh "ansible-playbook -i inventory all.yml --tags='deploy'"
        //         }
        //     }
        // }
    }
}
