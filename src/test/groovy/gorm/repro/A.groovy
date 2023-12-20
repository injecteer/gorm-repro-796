package gorm.repro

import org.grails.datastore.gorm.GormEntity

import grails.gorm.annotation.Entity

@Entity
class A implements GormEntity<A> {
  String name
  static mapping = {
    version false
  }
}
