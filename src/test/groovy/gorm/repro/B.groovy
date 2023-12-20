package gorm.repro

import org.grails.datastore.gorm.GormEntity

import grails.gorm.annotation.Entity

@Entity 
class B implements GormEntity<B> {
  String name
}
