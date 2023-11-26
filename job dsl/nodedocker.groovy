job("node js project ver 2"){
    description("this project will clone node js proj and build and push it to docker hub")
     scm {
        git('https://Fahad77-create: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@github.com/Fahad77-create/simple-node-jenkins-test.git', branch: 'master') { node -> 
            node / gitConfigName('Fahad77-create')
            node / gitConfigEmail('fahadkhaique070103@gmail.com')
        }
    }
    wrappers {
        // to setup nodejs enviroument
          nodejs('node-18')
          credentialsBinding {
            usernamePassword('USERNAME_DOCKER', 'PASS_DOCKER','dockerhubCred')
                                                                 //ID
        }
    }
    // commands to be executed
    steps{
        shell('npm install')
        shell('docker login -u ${USERNAME_DOCKER} -p ${PASS_DOCKER}')
        dockerBuildAndPublish {
            repositoryName('mukeshphulwani66/nodejs-jenkins-demo')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubCred')

         // defaults   
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
         shell('docker logout')
    }
}