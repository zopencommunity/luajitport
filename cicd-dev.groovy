node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/LuaJITport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/LuaJITport.git'), string(name: 'PORT_DESCRIPTION', value: 'LuaJIT — a Just-In-Time Compiler for Lua.' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
