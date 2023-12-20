package gorm.repro

import org.grails.orm.hibernate.HibernateDatastore

import grails.gorm.transactions.Transactional
import spock.lang.Specification

class NoApplicationGroovySpec extends Specification {
  
  def setupSpec() {
    Map config = [
      'dataSource.driverClassName': 'org.h2.Driver',
      'dataSource.url':'jdbc:h2:mem:myDB;DB_CLOSE_DELAY=-1',
      'hibernate.hbm2ddl.auto':'create-drop',
  ]
    new HibernateDatastore( config, [ A, B ] as Class[] )
  }
  
  @Transactional
  def 'no application.groovy involved'() {
    when: // persist 2 instances
    new A( name:'111' ).save()
    new B( name:'111' ).save()
    
    then: // A should NOT have a version, whereas B should
    null == A.list().first().version
    0 == B.list().first().version
  }
}
