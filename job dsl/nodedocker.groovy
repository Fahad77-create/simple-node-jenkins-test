job("node js project ver 2"){
    description("this project will clone node js proj and build and push it to docker hub")
     scm {
        git('https://github.com/fahad77-create/simple-node-jenkins-test.git','main') { node -> 
            node / gitConfigName('fahad77-create')
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
        shell('docker login -u ${USERNAME_DOC} -p ${PASS_DOC}')
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