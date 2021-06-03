job('ejemplo2-job-dsl'){
  description('JOB DSL de ejemplo para el curso de jenkins')
  scm{
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main'){
    	node-> 
      	node / gitConfigName('ivanbaltierra')
      	node / gitConfigEmail('ivan.baltierra@gmail.com')
    }
  }
  parameters{
    stringParam('nombre', defaultValue = 'Ivan B', description = 'Parametro de cadena para el Job Booleano')
    choiceParam('planeta', ['Tierra', 'Marte', 'Pluton'])
    booleanParam('agente', false)
  }
  triggers{
  	cron('H/7 * * * *')
  }
  steps{
  	shell("bash jobscript.sh")
  }
  publishers {
    mailer('ivan.baltierra.hop@gmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
