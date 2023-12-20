package gorm.repro

import org.grails.orm.hibernate.HibernateDatastore

import grails.gorm.transactions.Transactional
import spock.lang.Specification

class WithApplicationGroovySpec extends Specification {
  
  def setupSpec() {
    Map config = [
      'dataSource.driverClassName': 'org.h2.Driver',
      'dataSource.url':'jdbc:h2:mem:myDB;DB_CLOSE_DELAY=-1',
      'hibernate.hbm2ddl.auto':'create-drop',
  ]
    new HibernateDatastore( config, [ A, B ] as Class[] )
  }
  
  @Transactional
  def 'application.groovy should be read'() {
    when: // persist 2 instances
    new A( name:'111' ).save()
    new B( name:'111' ).save()
    
    then: // Both entities shall have no version field
    null == A.list().last().version
    null == B.list().last().version
  }
}
