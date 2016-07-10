package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReferenceContainer 
{
	private final List<Reference> references;

	public ReferenceContainer(Set<Reference> references) 
	{
		this.references = new ArrayList<>();
		this.references.addAll(references);
	}

	public List<Reference> getReferences()
	{
		return references;
	}
	
	public Reference getReference(String id)
	{
		Reference selectedReference;
		List<Reference> refList = selectReference(id);
		
		if (refList.size() == 0)
		{
			selectedReference = Reference.EMPTY_REFERENCE;
		}
		else
		{
			selectedReference = refList.get(0);			
		}
		return selectedReference;
	}
	
	private List<Reference> selectReference(String id)
	{
		return references.stream()
				.filter((reference)->{return reference.getId().equals(id);}).collect(Collectors.toCollection(ArrayList::new));
	}
	
}
