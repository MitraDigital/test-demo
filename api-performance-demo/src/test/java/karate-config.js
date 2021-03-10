function fn() {    
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);

  if (!env) {
    env = 'dev';
  }

  var config = {

    url : 'http://add9a025cb6354e6fba0b46fd2cf2dd4-1410882696.ap-south-1.elb.amazonaws.com/'

  }


  if (env == 'dev') {

    config.url = 'http://add9a025cb6354e6fba0b46fd2cf2dd4-1410882696.ap-south-1.elb.amazonaws.com/'


  } else if (env == 'qa') {

    config.url = 'http://add9a025cb6354e6fba0b46fd2cf2dd4-1410882696.ap-south-1.elb.amazonaws.com/'

  }
  return config;
}