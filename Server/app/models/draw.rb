class Draw < ActiveRecord::Base
	attr_accessible :challenge, :description, :draw, :id_creator, :latitude, :longitude, :password
	reverse_geocoded_by :latitude, :longitude,
	:address => :location
	after_validation :reverse_geocode
end