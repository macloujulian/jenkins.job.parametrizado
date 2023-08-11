job('ejemplo-job-DSL') {
  description('Job DSL para el ejemplo para el curso')
  scm {
    git ('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
    	node / gitConfigName ( 'margri764' )
        node / gitConfigEmail ( 'margri764@gmail.com' )     
    }
  }
  parameters {
    stringParam( 'nombre', defaultValue = 'Marcelo', description = 'Parametro de cadena para el job booleano' )
    choiceParam( 'planeta', ['Mercurio', 'Marte', 'Venus'] )
    booleanParam('agente', false)
  }
  triggers{
  	cron('H/7 * * * *')
  }
  steps{
  	shell( "bash jobscript.s" )
  }
  publishers {
    mailer ('margri764@gmail.com', true, true)
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
