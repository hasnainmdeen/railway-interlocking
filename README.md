# Railway Interlocking API

This API provides functionality to check for conflicts in railway tracks based on a given station graph and routes.

## `check_conflicts` Endpoint

### Description

Checks whether a given route conflicts with any occupied routes in the station.

The method assumes that train can travel in any direction.
The `PathFinderService` is injected into the `RailwayController`.

The program works as follows:
1. We first find the path for the route that we need to check
2. If we can not find the path for the given route we return false
3. If we found the path for the given route, we do the following steps 
   1. We filter out the occupied routes from the given list of routes
   2. We then find the path for each occupied route
   3. Finally, we check if the path for the given route overlaps with the path of any occupied route
   4. If we find an overlap, it means we detected a conflict and return `true`
   5. Else, we return `false` because path for the given route does not overlap with the path of any occupied routes.

In order to traverse the station graph, we have implemented a method named `findPath` which uses the BFS algorithm to traverse the station graph
We also have method named `checkConflicts` that checks for the conflict
Another method `sectionExistInPath` checks weather the given section overlaps with a path

### Request

- **URL**: `/railway/check_conflicts`
- **Method**: `POST`
- **Content-Type**: `application/json`

#### Body:

```json
{
	"station_graph": [
		{
			"start": "Station West",
			"end": "Entry Signal West"
		},
		{
			"start": "Entry Signal West",
			"end": "Point 1"
		},
		{
			"start": "Point 1",
			"end": "Exit Signal West 1"
		},
		{
			"start": "Point 1",
			"end": "Exit Signal West 2"
		},
		{
			"start": "Exit Signal West 1",
			"end": "Exit Signal East 1"
		},
		{
			"start": "Exit Signal West 2",
			"end": "Exit Signal East 2"
		},
		{
			"start": "Exit Signal East 1",
			"end": "Point 2"
		},
		{
			"start": "Exit Signal East 2",
			"end": "Point 2"
		},
		{
			"start": "Point 2",
			"end": "Entry Signal East"
		},
		{
			"start": "Entry Signal East",
			"end": "Station East"
		}
	],
	"routes": [
		{
			"start": "Entry Signal West",
			"end": "Exit Signal East 1",
			"occupied": false
		},
		{
			"start": "Entry Signal West",
			"end": "Exit Signal East 2",
			"occupied": false
		},
		{
			"start": "Exit Signal East 1",
			"end": "Station East",
			"occupied": false
		},
		{
			"start": "Exit Signal East 2",
			"end": "Station East",
			"occupied": false
		},
		{
			"start": "Entry Signal East",
			"end": "Exit Signal West 1",
			"occupied": false
		},
		{
			"start": "Entry Signal East",
			"end": "Exit Signal West 2",
			"occupied": false
		},
		{
			"start": "Exit Signal West 1",
			"end": "Station West",
			"occupied": true
		},
		{
			"start": "Exit Signal West 2",
			"end": "Station West",
			"occupied": true
		}
	],
	"check_route": {
		"start": "Point 1",
		"end": "Exit Signal West 2"
	}
}

#
